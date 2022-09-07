package org.paranora.mapstruct.starter.core.java.metadata.creator;

import org.paranora.mapstruct.starter.core.java.metadata.entity.Meta;

public interface MetaCreator<S extends Object, D extends Meta> {
    D create(S source);

}
