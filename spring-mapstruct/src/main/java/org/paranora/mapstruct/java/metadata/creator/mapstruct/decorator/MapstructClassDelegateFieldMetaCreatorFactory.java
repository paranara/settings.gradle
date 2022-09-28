package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.FieldMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.FieldMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public class MapstructClassDelegateFieldMetaCreatorFactory implements FieldMetaCreatorFactory<InterfaceMeta, ClassMeta> {

    @Override
    public FieldMetaCreator<InterfaceMeta, ClassMeta> fIeldMetaCreator() {
        return new MapstructClassDelegateFieldMetaCreator();
    }

    @Override
    public AnnotationMetaCreator<InterfaceMeta, ClassMeta> annotationCreator() {
        return new MapstructClassDelegateFieldAnnotationMetaCreator();
    }
}
