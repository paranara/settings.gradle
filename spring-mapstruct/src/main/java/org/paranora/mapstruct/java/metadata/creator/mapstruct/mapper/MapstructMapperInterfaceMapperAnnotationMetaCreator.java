package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.Arrays;

public class MapstructMapperInterfaceMapperAnnotationMetaCreator extends AbsMetadataInterfaceAnnotationMetaCreator {

    @Override
    public AnnotationMeta create(ClassMeta source, ClassMeta sourceMeta, Class<?> clasz) {
        return AnnotationMeta.builder()
                .name(Mapper.class.getSimpleName())
                .packageName(Mapper.class.getPackage().getName())
                .fields(Arrays.asList(
                        AnnotationFieldMeta.builder()
                                .name("componentModel")
                                .typeName(TypeName.get(String.class))
                                .value("spring")
                                .build()
                        , AnnotationFieldMeta.builder()
                                .name("nullValueCheckStrategy")
                                .typeName(TypeName.get(NullValueCheckStrategy.class))
                                .value(NullValueCheckStrategy.ALWAYS)
                                .build()
                        , AnnotationFieldMeta.builder()
                                .name("nullValueMappingStrategy")
                                .typeName(TypeName.get(NullValueMappingStrategy.class))
                                .value(NullValueMappingStrategy.RETURN_DEFAULT)
                                .build()
                ))
                .build();
    }
}
