package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface MetaCreator<S extends Object, T extends Meta> {
    T create(S source, Meta meta, Class<?> clasz);

}
