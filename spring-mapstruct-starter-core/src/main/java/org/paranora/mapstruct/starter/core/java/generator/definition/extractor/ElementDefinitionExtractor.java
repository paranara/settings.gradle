package org.paranora.mapstruct.starter.core.java.generator.definition.extractor;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;

import javax.lang.model.element.Element;

public interface ElementDefinitionExtractor<S extends Element,D extends MetaDefinition> extends DefinitionExtractor<S, D> {
}
