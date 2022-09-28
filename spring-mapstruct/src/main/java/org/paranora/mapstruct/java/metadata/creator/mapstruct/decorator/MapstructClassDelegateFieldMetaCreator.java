package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.converter.MapstructMapperConversionService;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructFieldMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapstructClassDelegateFieldMetaCreator extends AbsMapstructFieldMetaCreator<InterfaceMeta, ClassMeta> {


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
                .typeName(TypeName.get(MapstructMapperConversionService.class))
                .annotations(Arrays.asList(
                        AnnotationMeta.builder()
                                .name(Autowired.class.getSimpleName())
                                .typeName(TypeName.get(Autowired.class))
                                .packageName(Autowired.class.getPackage().getName())
                                .build()
                        , AnnotationMeta.builder()
                                .name(Lazy.class.getSimpleName())
                                .typeName(TypeName.get(Lazy.class))
                                .packageName(Lazy.class.getPackage().getName())
                                .build()))
                .build());
        return fieldMetas;
    }
}