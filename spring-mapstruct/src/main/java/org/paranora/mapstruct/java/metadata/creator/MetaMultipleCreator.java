package org.paranora.mapstruct.java.metadata.creator;


import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;


public interface MetaMultipleCreator<S extends Object, T extends Meta, TP extends Meta> extends MetaCreator<S, T, TP> {
    List<T> creates(S source, TP parent, Class<?> clasz);
}
