package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.converter.MapstructConversionService;
import org.paranora.mapstruct.converter.MapstructMapperConversionService;
import org.paranora.mapstruct.java.metadata.entity.*;
import org.springframework.core.convert.converter.Converter;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.Arrays;
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
                        System.out.println(String.format("======================>>>>>>>>>>>>> typeName : %s"));
                        String sourceName = a.field(PMapping.SOURCE).value().value(String.class);
                        String targetName = a.field(PMapping.TARGET).value().value(String.class);
                        TypeName sourceType = a.field(PMapping.SOURCETYPE).value().value(TypeName.class);
                        TypeName targetType = a.field(PMapping.TARGETTYPE).value().value(TypeName.class);
                        String sourceVarName = String.format("%sValue_s", sourceName);
                        String targetVarName = String.format("%sValue_t", targetName);
                        builder.addStatement("$T $L = $L.get$L()", sourceType, sourceVarName, parameterName, sourceName.substring(0, 1).toUpperCase() + sourceName.substring(1))
                                .beginControlFlow("if(null!=$L)", sourceVarName)
                                .addStatement("$T $L = $L.$L($L,$T.class)", targetType, targetVarName, conversionServiceName, MethodName, sourceVarName, targetType)
                                .addStatement("$L.set$L($L)", resultName, targetName.substring(0, 1).toUpperCase() + targetName.substring(1), targetVarName)
                                .endControlFlow();
                    }
                });

        builder.addStatement("return $L", resultName);
        return builder.build();
    }

}
