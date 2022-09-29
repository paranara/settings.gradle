package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;
import org.paranora.mapstruct.java.metadata.entity.ValueMeta;

import javax.lang.model.element.Modifier;
import java.util.Arrays;

public class MapstructClassConverterMethodMetaCreator extends AbsMapstructClassMethodMetaCreator<InterfaceMeta, ClassMeta> {

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
        return null;
    }
}
