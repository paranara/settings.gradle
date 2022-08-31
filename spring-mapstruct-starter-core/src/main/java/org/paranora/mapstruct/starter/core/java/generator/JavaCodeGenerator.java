package org.paranora.mapstruct.starter.core.java.generator;

import org.paranora.mapstruct.starter.core.java.metadata.entity.Meta;

public interface JavaCodeGenerator<D extends Meta, TC extends Object> {
    TC create(D definition);
}
