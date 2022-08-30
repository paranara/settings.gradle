package org.paranora.mapstruct.starter.core.java.generator;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;

public interface JavaCodeGenerator<D extends MetaDefinition, TC extends Object> {
    TC create(D definition);
}
