package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface MetaCreator<S extends Object,D extends Meta> {
    D create(S source, Class<?> clasz);

}
