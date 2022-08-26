package org.paranora.mapstruct.starter.core.java.generator.definition;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.Definition;

public interface DefinitionConverter<S extends Definition,T extends Definition> {
    T convert(S source,String targetClassName);
    T convert(S source,Class targetClass);
}
