package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.TypeMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.ClassMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.FieldMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructClassMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MapstructClassMetaCreatorFactory implements ClassMetaCreatorFactory {


    @Override
    public List<FieldMetaCreatorFactory> fieldMetaCreatorFactorys() {
        return Arrays.asList(new MapstructClassDelegateFieldMetaCreatorFactory());
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