package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.MethodMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface MethodMetaCreatorFactory<S extends Object,TP extends Meta> extends MetaCreatorFactory<S,TP> {
    MethodMetaCreator<S,TP> methodCreator();
    ParameterMetaCreatorFactory<S,TP>  parameterCreatorFactory();

}
