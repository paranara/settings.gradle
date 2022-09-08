package org.paranora.mapstruct.java.metadata.creator.factory;



import org.paranora.mapstruct.java.metadata.creator.ParameterMetaCreator;

import java.util.List;

public interface ParameterMetaCreatorFactory<S extends Object> extends MetaCreatorFactory<S> {
    List<ParameterMetaCreator<S>> parameterCreators();
}
