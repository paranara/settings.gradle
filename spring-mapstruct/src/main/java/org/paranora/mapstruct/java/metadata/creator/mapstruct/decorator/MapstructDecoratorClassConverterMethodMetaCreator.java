package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.converter.MapstructMapperConversionService;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;
import org.paranora.mapstruct.java.metadata.entity.ValueMeta;
import org.springframework.core.convert.converter.Converter;

import javax.lang.model.element.Modifier;
import java.util.Arrays;

public class MapstructDecoratorClassConverterMethodMetaCreator extends AbsMapstructClassMethodMetaCreator<InterfaceMeta, ClassMeta> {

    @Override
    public MethodMeta create(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        MethodMeta meta = MethodMeta.builder()
                .accessLevels(Arrays.asList(Modifier.PUBLIC, Modifier.ABSTRACT))
                .returnType(parent.getSuperInterfaces().get(0).getGenericTypes().get(1))
                .name("convert")
                .value(ValueMeta.builder()
                        .typeName(TypeName.get(CodeBlock.class))
                        .value(createBody(source, parent, clasz))
                        .build())
                .build();
        return meta;
    }

    @Override
    protected CodeBlock createBody(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        InterfaceMeta superInterface = source.getSuperInterfaces().stream().filter(s -> s.getTypeName().equals(TypeName.get(Converter.class))).findFirst().get();
        TypeName sourceTypeName = superInterface.getGenericTypes().get(0);
        TypeName targetTypeName = superInterface.getGenericTypes().get(1);

        String mapperConversionServiceName = parent.getFields().values().stream().filter(f -> f.getTypeName().equals(TypeName.get(MapstructMapperConversionService.class))).findFirst().get().getName();
        String resultName = "result";
        String parameterName = source.getMethods().get(0).getParameters().get(0).getName();
        String methodName = source.getMethods().get(0).getName();
        CodeBlock.Builder builder = CodeBlock.builder();
        builder.addStatement("$T $L = $L.$L($L)", targetTypeName, resultName, mapperConversionServiceName, methodName, parameterName);

        builder.add("return $L", resultName);
        return builder.build();
    }
}
