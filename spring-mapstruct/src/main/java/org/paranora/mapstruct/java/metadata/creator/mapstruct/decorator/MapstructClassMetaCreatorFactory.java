package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.TypeMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.ClassMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.FieldMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;

import java.util.Arrays;
import java.util.List;

public class MapstructClassMetaCreatorFactory implements ClassMetaCreatorFactory {


    @Override
    public List<FieldMetaCreatorFactory> fieldMetaCreatorFactorys() {
        return Arrays.asList(new MapstructClassFieldMetaCreatorFactory());
    }

    @Override
    public AnnotationMetaCreator annotationCreator() {
        return new MapstructClassAnnotationMetaCreator();
    }

    @Override
    public List<MethodMetaCreatorFactory> methodCreatorFactorys() {
        return null;
    }

    @Override
    public TypeMetaCreator typeMetaCreator() {
        return new MapstructClassMetaCreator();
    }
}