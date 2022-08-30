package org.paranora.mapstruct.starter.core.java.generator.definition.creator;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;

import java.util.List;

public interface DefinitionCreator<S extends Object, D extends MetaDefinition> {
    List<D> extract(S source);

}
