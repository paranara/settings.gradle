package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

public class MapstructMapperInterfaceAnnotationMetaCreator implements AnnotationMetaCreator<ClassMeta> {

    @Override
    public AnnotationMeta create(ClassMeta source, Class<?> clasz) {
        return AnnotationMeta.builder().build();
    }
}
