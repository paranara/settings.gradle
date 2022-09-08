package org.paranora.mapstruct.java.metadata.creator.mapstruct;



import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.InterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import java.util.List;

public class MapstructMapperInterfaceMetaCreatorFactory implements InterfaceMetaCreatorFactory<ClassMeta> {


    @Override
    public List<MethodMetaCreatorFactory<ClassMeta>> methodCreatorFactorys() {
        return null;
    }

    @Override
    public InterfaceMetaCreator<ClassMeta> InterfaceCreator() {
        return new MapstructMapperInterfaceMetaCreator();
    }

    @Override
    public List<AnnotationMetaCreator<ClassMeta>> annotationCreators() {
        return null;
    }
}
