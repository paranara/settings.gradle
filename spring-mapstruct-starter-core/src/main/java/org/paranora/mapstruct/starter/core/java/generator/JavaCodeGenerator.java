package org.paranora.mapstruct.starter.core.java.generator;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.Definition;

public interface JavaCodeGenerator<D extends Definition, T extends Object> {
    T create(D definition);
}
