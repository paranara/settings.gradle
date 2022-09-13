package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface MetaCreator<S extends Object, T extends Meta, TP extends Meta> {
    T create(S source, TP meta, Class<?> clasz);

}
