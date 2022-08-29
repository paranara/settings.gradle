package org.paranora.mapstruct.starter.core.java.generator.definition;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;

public abstract class AbsDefinitionConverter<S extends MetaDefinition,T extends MetaDefinition> implements DefinitionConverter<S,T>{

    @Override
    public T convert(S source, String targetClassName) {
        try {
            return convert(source,Class.forName(targetClassName));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
