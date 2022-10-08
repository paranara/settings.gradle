package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.ClassName;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructFieldMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapstructDecoratorClassDelegateFieldMetaCreator extends AbsMapstructFieldMetaCreator<InterfaceMeta, ClassMeta> {

    @Override
    public FieldMeta create(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        return null;
    }

    @Override
    public List<FieldMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        List<FieldMeta> fieldMetas = new ArrayList<>();
        fieldMetas.add(FieldMeta.builder()
                .name("delegate")
                .accessLevels(Arrays.asList(Modifier.PRIVATE))
                .typeName(ClassName.get(source.getPackageName(),source.getName()))
                .build());

        return fieldMetas;
    }
}