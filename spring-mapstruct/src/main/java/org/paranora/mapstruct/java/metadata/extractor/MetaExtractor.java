package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface MetaExtractor<S extends Object, D extends Meta> {
    List<D> extract(S source);

}
