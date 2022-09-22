package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface MetaMultipleExtractor<S extends Object, D extends Meta> extends MetaExtractor<S,D> {
    List<D> extracts(S source);

}
