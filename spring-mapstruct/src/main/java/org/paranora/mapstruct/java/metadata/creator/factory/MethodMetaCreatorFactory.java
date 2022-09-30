package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.MethodMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.ParameterMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

public interface MethodMetaCreatorFactory<S extends Object, TP extends Meta> extends MetaCreatorFactory<S, MethodMeta, TP> {
    MethodMetaCreator<S, TP> methodCreator();

    ParameterMetaCreator parameterCreator();

}
