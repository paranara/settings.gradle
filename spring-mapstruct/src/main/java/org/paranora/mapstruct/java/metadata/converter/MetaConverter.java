package org.paranora.mapstruct.java.metadata.converter;

import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface MetaConverter<S extends Object,T extends Meta> {
    List<T> convert(S source);
}
