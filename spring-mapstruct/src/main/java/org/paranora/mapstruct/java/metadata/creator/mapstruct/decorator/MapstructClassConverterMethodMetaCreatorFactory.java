package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.MethodMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.ParameterMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public class MapstructClassConverterMethodMetaCreatorFactory implements MethodMetaCreatorFactory<InterfaceMeta,ClassMeta> {

    @Override
    public AnnotationMetaCreator<InterfaceMeta, ClassMeta> annotationCreator() {
        return new MapstructClassConverterMethodAnnotationMetaCreator();
    }

    @Override
    public MethodMetaCreator<InterfaceMeta, ClassMeta> methodCreator() {
        return new MapstructClassConverterMethodMetaCreator();
    }

    @Override
    public ParameterMetaCreator parameterCreator() {
        return new MapstructClassConverterMethodParameterMetaCreator();
    }
}
