package org.paranora.mapstruct.java.metadata.creator.factory;



import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;

import java.util.List;

public interface InterfaceMetaCreatorFactory<S extends Object> extends MetaCreatorFactory<S> {
    List<MethodMetaCreatorFactory<S>>  methodCreatorFactorys();

    InterfaceMetaCreator<S> InterfaceCreator();

}
