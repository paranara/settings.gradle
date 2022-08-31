package org.paranora.mapstruct.starter.core.java.metadata.extractor;

import org.paranora.mapstruct.starter.core.java.metadata.entity.Meta;

import javax.lang.model.element.Element;

public interface ElementMetaExtractor<S extends Element,D extends Meta> extends MetaExtractor<S, D> {
}
