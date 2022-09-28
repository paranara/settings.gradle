package org.paranora.mapstruct.java.code;

import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface JavaCodeGenerator<D extends Meta, TC extends Object> {
    TC create(D meta);
}
