package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import com.squareup.javapoet.TypeName;
import org.mapstruct.DecoratedWith;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.Arrays;

public class MapstructMapperInterfaceDecoratedWithAnnotationMetaCreator extends AbsMetadataInterfaceAnnotationMetaCreator {

    @Override
    public AnnotationMeta create(ClassMeta source, Meta meta, Class<?> clasz) {
        return AnnotationMeta.builder()
                .name(DecoratedWith.class.getSimpleName())
                .packageName(DecoratedWith.class.getPackage().getName())
                .fields(Arrays.asList(
                        AnnotationFieldMeta.builder()
                                .name("value")
                                .typeName(TypeName.get(String.class))
                                .value(String.format("%s.%s.class", meta.getPackageName(), meta.getName()))
                                .build()
                ))
                .build();
    }
}
