package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.MethodMetaCreator;

public interface MethodMetaCreatorFactory<S extends Object> extends MetaCreatorFactory<S> {
    MethodMetaCreator<S> methodCreator();
    ParameterMetaCreatorFactory<S>  parameterCreatorFactory();

}
