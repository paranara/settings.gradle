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

    @Override
    public MethodMeta create(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        MethodMeta meta = MethodMeta.builder()
                .accessLevels(Arrays.asList(Modifier.PUBLIC))
                .returnType(source.getSuperInterfaces().get(0).getGenericTypes().get(1))
                .name("convert")
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

//        String mapperConversionServiceName = parent.getFields().values().stream().filter(f -> f.getTypeName().equals(TypeName.get(MapstructMapperConversionService.class))).findFirst().get().getName();
        String conversionServiceName = parent.getFields().values().stream().filter(f -> f.getTypeName().equals(TypeName.get(MapstructConversionService.class))).findFirst().get().getName();
        String delegate = parent.getFields().values().stream().filter(f -> f.getName().equalsIgnoreCase("delegate")).findFirst().get().getName();
        String resultName = "result";
        String parameterName = source.getMethods().get(0).getParameters().get(0).getName();
        String methodName = source.getMethods().get(0).getName();
        CodeBlock.Builder builder = CodeBlock.builder();
//        builder.addStatement("$T $L = $L.$L($L,$L.class)", targetTypeName, resultName, mapperConversionServiceName, methodName, parameterName, targetTypeName);

        builder.addStatement("$T $L = $L.$L($L)", targetTypeName, resultName, delegate, methodName, parameterName);

        source.getMethods().stream()
                .filter(m -> m.getName().equalsIgnoreCase(methodMeta.getName()) && m.getReturnType().equals(methodMeta.getReturnType()))
                .forEach(m -> {
                    Optional<AnnotationMeta> opt = m.getAnnotations().stream().filter(a -> a.getTypeName().equals(TypeName.get(PMapping.class))).findFirst();
                    if (opt.isPresent() && true == (boolean) opt.get().getFields().get(PMapping.NEST).getValue().getValue() && opt.get().getFields().containsKey(PMapping.TARGETTYPE)) {
                        String sourceName = (String) opt.get().getFields().get(PMapping.SOURCE).getValue().getValue();
                        String targetName = (String) opt.get().getFields().get(PMapping.TARGET).getValue().getValue();
                        Object sourceTypeValue = opt.get().getFields().get(PMapping.SOURCETYPE).getValue().getValue();
                        Object targetTypeValue = opt.get().getFields().get(PMapping.TARGETTYPE).getValue().getValue();
                        TypeName sourceType = objectToTypeName(sourceTypeValue);
                        TypeName targetType = objectToTypeName(targetTypeValue);

                        String sourceVarName = String.format("%sValue_s", sourceName);
                        String targetVarName = String.format("%sValue_t", targetName);

                        builder.addStatement("$L $L=$L.get$L()", sourceType, sourceVarName, parameterName, sourceName.substring(0, 1).toUpperCase() + sourceName.substring(1));
                        builder.addStatement("$L $L=$L.$L($L,$L.class)", targetType, targetVarName, conversionServiceName, methodName, sourceVarName, targetType);
                        builder.addStatement("$L.set$L($L)", resultName, targetName.substring(0, 1).toUpperCase() + sourceName.substring(1),targetVarName);

                    }
                });

        builder.add("return $L;", resultName);
        return builder.build();
    }

    protected TypeName objectToTypeName(Object obj) {
        TypeName typeName = null;
        if (obj instanceof TypeMirror) {
            typeName = TypeName.get((TypeMirror) obj);
        } else if (obj instanceof Class) {
            typeName = TypeName.get((Class) obj);
        } else if (obj instanceof TypeName) {
            typeName = (TypeName) obj;
        }
        return typeName;
    }
}
