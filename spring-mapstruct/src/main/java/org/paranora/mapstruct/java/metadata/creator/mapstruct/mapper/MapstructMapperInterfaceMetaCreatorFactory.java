package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;


import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.InterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import java.util.Arrays;
import java.util.List;

public class MapstructMapperInterfaceMetaCreatorFactory implements InterfaceMetaCreatorFactory<ClassMeta, InterfaceMeta, InterfaceMeta> {

    @Override
    public List<MethodMetaCreatorFactory<ClassMeta, InterfaceMeta>> methodCreatorFactorys() {
        return Arrays.asList(new MapstructMapperInterfaceConverterMethodMetaCreatorFactory());
    }

    @Override
    public InterfaceMetaCreator<ClassMeta, InterfaceMeta> typeMetaCreator() {
        return new MapstructMapperInterfaceMetaCreator();
    }

    @Override
    public AnnotationMetaCreator<ClassMeta, InterfaceMeta> annotationCreator() {
        return new MapstructMapperInterfaceAnnotationMetaCreator();
    }
}
