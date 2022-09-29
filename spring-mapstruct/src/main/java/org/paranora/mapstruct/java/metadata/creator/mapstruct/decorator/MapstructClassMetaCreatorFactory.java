package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.TypeMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.ClassMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.FieldMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import java.util.Arrays;
import java.util.List;

public class MapstructClassMetaCreatorFactory implements ClassMetaCreatorFactory<InterfaceMeta, ClassMeta> {


    @Override
    public List<FieldMetaCreatorFactory<InterfaceMeta, ClassMeta>> fieldMetaCreatorFactorys() {
        return Arrays.asList(new MapstructClassFieldMetaCreatorFactory());
    }

    @Override
    public AnnotationMetaCreator<InterfaceMeta, ClassMeta> annotationCreator() {
        return new MapstructClassAnnotationMetaCreator();
    }

    @Override
    public List<MethodMetaCreatorFactory<InterfaceMeta, ClassMeta>> methodCreatorFactorys() {
        return Arrays.asList(new MapstructClassConverterMethodMetaCreatorFactory());
    }

    @Override
    public TypeMetaCreator typeMetaCreator() {
        return new MapstructClassMetaCreator();
    }
}