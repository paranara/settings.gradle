package org.paranora.mapstruct.java.metadata.merger;

import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface MetaMerger<T extends Meta> {
    T merge(T source, T target);
}
