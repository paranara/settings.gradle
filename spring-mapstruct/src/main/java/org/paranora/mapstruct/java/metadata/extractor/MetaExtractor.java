package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface MetaExtractor<S extends Object, D extends Meta> {
    D extract(S source);

}
