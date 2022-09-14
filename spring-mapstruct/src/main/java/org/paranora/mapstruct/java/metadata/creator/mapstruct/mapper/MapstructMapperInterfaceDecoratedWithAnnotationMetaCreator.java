package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.DecoratedWith;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.Arrays;

public class MapstructMapperInterfaceDecoratedWithAnnotationMetaCreator extends AbsMetadataInterfaceAnnotationMetaCreator {

    @Override
    public AnnotationMeta create(ClassMeta source, ClassMeta sourceMeta, Class<?> clasz) {
        return AnnotationMeta.builder()
                .name(DecoratedWith.class.getSimpleName())
                .packageName(DecoratedWith.class.getPackage().getName())
                .fields(Arrays.asList(
                        AnnotationFieldMeta.builder()
                                .name("value")
                                .typeName(TypeName.get(Class.class))
                                .value(String.format("%s.%sDecorator.class", sourceMeta.getPackageName(), sourceMeta.getName()))
                                .build()
                ))
                .build();
    }
}
