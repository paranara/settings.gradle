package org.paranora.mapstruct.starter.core.java.generator.definition;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.Definition;

import java.util.List;

public interface DefinitionExtractor<S extends Object, D extends Definition> {
    List<D> extract(S source);

}
