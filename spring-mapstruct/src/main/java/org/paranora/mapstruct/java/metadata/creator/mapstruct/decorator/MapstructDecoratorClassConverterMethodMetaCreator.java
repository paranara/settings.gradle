package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.converter.MapstructConversionService;
import org.paranora.mapstruct.java.metadata.entity.*;
import org.springframework.core.convert.converter.Converter;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MapstructDecoratorClassConverterMethodMetaCreator extends AbsMapstructClassMethodMetaCreator<InterfaceMeta, ClassMeta> {

    private final static String MethodName = "convert";

    @Override
    public MethodMeta create(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        MethodMeta meta = MethodMeta.builder()
                .accessLevels(Arrays.asList(Modifier.PUBLIC))
                .returnType(source.getSuperInterfaces().get(0).getGenericTypes().get(1))
                .name(MethodName)
                .build();

        meta.setValue(ValueMeta.builder()
                .typeName(TypeName.get(CodeBlock.class))
                .value(createBody(source, parent, meta, clasz))
                .build());
        return meta;
    }

    @Override
    protected CodeBlock createBody(InterfaceMeta source, ClassMeta parent, MethodMeta methodMeta, Class<?> clasz) {
        InterfaceMeta superInterface = source.getSuperInterfaces().stream().filter(s -> s.getTypeName().equals(TypeName.get(Converter.class))).findFirst().get();
        TypeName sourceTypeName = superInterface.getGenericTypes().get(0);
        TypeName targetTypeName = superInterface.getGenericTypes().get(1);

        String conversionServiceName = parent.getFields().values().stream().filter(f -> f.getTypeName().equals(TypeName.get(MapstructConversionService.class))).findFirst().get().getName();
        String delegate = parent.getFields().values().stream().filter(f -> f.getName().equalsIgnoreCase("delegate")).findFirst().get().getName();
        String resultName = "result";
        String parameterName = source.getMethods().get(0).getParameters().get(0).getName();
        CodeBlock.Builder builder = CodeBlock.builder();
        builder.addStatement("$T $L = $L.$L($L)", targetTypeName, resultName, delegate, MethodName, parameterName);
        source.getMethods().stream()
                .filter(m -> m.getName().equalsIgnoreCase(methodMeta.getName()) && m.getReturnType().equals(methodMeta.getReturnType()))
                .forEach(m -> {
                    Optional<AnnotationMeta> opt = m.getAnnotations().stream().filter(a -> a.getTypeName().equals(TypeName.get(PMapping.class))).findFirst();
                    if (opt.isPresent() && true == opt.get().field(PMapping.NEST).value().value(Boolean.class) && opt.get().containsField(PMapping.TARGETTYPE)) {
                        AnnotationMeta a = opt.get();
                        String sourceName = a.field(PMapping.SOURCE).value().value(String.class);
                        String targetName = a.field(PMapping.TARGET).value().value(String.class);
                        TypeName sourceType = a.field(PMapping.SOURCETYPE).value().value(TypeName.class);
                        TypeName targetType = a.field(PMapping.TARGETTYPE).value().value(TypeName.class);
                        String sourceVarName = String.format("%sValue_s", sourceName);
                        String targetVarName = String.format("%sValue_t", targetName);
                        String sourceMethodName = sourceName.substring(0, 1).toUpperCase() + sourceName.substring(1);
                        String targetMethodName = targetName.substring(0, 1).toUpperCase() + targetName.substring(1);

                        if (a.field(PMapping.SOURCETYPE).getTypeName() instanceof ClassName) {
                            builder.addStatement("$T $L = $L.get$L()", sourceType, sourceVarName, parameterName, sourceMethodName)
                                    .beginControlFlow("if(null!=$L)", sourceVarName)
                                    .addStatement("$T $L = $L.$L($L,$T.class)", targetType, targetVarName, conversionServiceName, MethodName, sourceVarName, targetType)
                                    .addStatement("$L.set$L($L)", resultName, targetMethodName, targetVarName)
                                    .endControlFlow();
                        } else if (a.field(PMapping.SOURCETYPE).getTypeName() instanceof ParameterizedTypeName) {
                            ParameterizedTypeName pt = (ParameterizedTypeName) a.field(PMapping.SOURCETYPE).getTypeName();
                            if (pt.rawType.equals(ClassName.get(List.class))) {
                                builder.addStatement("$T<$T> $L = $L.get$L()", TypeName.get(List.class), sourceType, sourceVarName, parameterName, sourceMethodName)
                                        .beginControlFlow("if(null!=$L && $L.size()>0)", sourceVarName, sourceVarName)
                                        .addStatement("$L.set$L(new $T())", resultName, sourceMethodName, ArrayList.class)
                                        .beginControlFlow("for($T v : $L)", sourceType, sourceVarName)
                                        .addStatement("$T $L = $L.$L($L,$T.class)", targetType, targetVarName, conversionServiceName, MethodName, "v", targetType)
                                        .addStatement("$L.get$L().add($L)", resultName, targetMethodName, targetVarName)
                                        .endControlFlow()
                                        .endControlFlow();
                            }
                        } else {

                        }
                    }
                });

        builder.addStatement("return $L", resultName);
        return builder.build();
    }

}
