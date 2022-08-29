package org.paranora.mapstruct.starter.core.java.generator.definition;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;

import java.util.List;

public interface DefinitionExtractor<S extends Object, D extends MetaDefinition> {
    List<D> extract(S source);

}
