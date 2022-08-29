package org.paranora.mapstruct.starter.core.java.generator.definition;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;

public interface DefinitionConverter<S extends MetaDefinition,T extends MetaDefinition> {
    T convert(S source,String targetClassName);
    T convert(S source,Class targetClass);
}
