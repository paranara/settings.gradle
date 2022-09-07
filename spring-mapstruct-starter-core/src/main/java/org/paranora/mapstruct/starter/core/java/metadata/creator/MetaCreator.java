package org.paranora.mapstruct.starter.core.java.metadata.creator;

import org.paranora.mapstruct.starter.core.java.metadata.entity.Meta;

public interface MetaCreator<D extends Meta> {
    D create(Object source, Class<?> clasz);

}
