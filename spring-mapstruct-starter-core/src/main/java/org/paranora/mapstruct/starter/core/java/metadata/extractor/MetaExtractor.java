package org.paranora.mapstruct.starter.core.java.metadata.extractor;

import org.paranora.mapstruct.starter.core.java.metadata.entity.Meta;

import java.util.List;

public interface MetaExtractor<S extends Object, D extends Meta> {
    List<D> extract(S source);

}
