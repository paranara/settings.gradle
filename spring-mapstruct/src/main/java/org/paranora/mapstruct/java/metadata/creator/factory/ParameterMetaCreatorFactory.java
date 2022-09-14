package org.paranora.mapstruct.java.metadata.creator.factory;



import org.paranora.mapstruct.java.metadata.creator.ParameterMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface ParameterMetaCreatorFactory<S extends Object,TP extends Meta> extends MetaCreatorFactory<S,TP> {
    List<ParameterMetaCreator<S,TP>> parameterCreators();
}
