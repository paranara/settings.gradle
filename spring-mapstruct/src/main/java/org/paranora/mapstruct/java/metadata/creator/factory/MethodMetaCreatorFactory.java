package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.MethodMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.ParameterMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface MethodMetaCreatorFactory<S extends Object,TP extends Meta> extends MetaCreatorFactory<S,TP> {
    MethodMetaCreator<S,TP> methodCreator();
    ParameterMetaCreator<S,TP> parameterCreator();

}
