package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.MethodMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.ParameterMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public class MapstructMapperInterfaceConverterMethodMetaCreatorFactory implements MethodMetaCreatorFactory<ClassMeta, InterfaceMeta> {

    @Override
    public AnnotationMetaCreator<ClassMeta, InterfaceMeta> annotationCreator() {
        return new MapstructMapperInterfaceConverterMethodAnnotationMetaCreator();
    }

    @Override
    public MethodMetaCreator<ClassMeta, InterfaceMeta> methodCreator() {
        return new MapstructMapperInterfaceConverterMethodMetaCreator();
    }

    @Override
    public ParameterMetaCreator<ClassMeta, InterfaceMeta> parameterCreator() {
        return new MapstructMapperInterfaceConverterMethodSourceParameterMetaCreator();
    }

}
