package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.converter.MapstructMapperInterfaceAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public abstract class AbsMapstructAnnotationMetaCreator implements AnnotationMetaCreator<ClassMeta, InterfaceMeta> {

    public AbsMapstructAnnotationMetaCreator() {
        init();
    }

    protected void init() {

    }

}
