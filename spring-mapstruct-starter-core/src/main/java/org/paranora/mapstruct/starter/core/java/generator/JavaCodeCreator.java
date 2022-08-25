package org.paranora.mapstruct.starter.core.java.generator;

import org.paranora.mapstruct.starter.core.java.generator.entity.Definition;

public interface JavaCodeCreator<D extends Definition, T extends Object> {
    T create(D definition);
}
