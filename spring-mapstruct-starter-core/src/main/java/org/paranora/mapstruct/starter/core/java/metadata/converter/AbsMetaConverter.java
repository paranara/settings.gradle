package org.paranora.mapstruct.starter.core.java.metadata.converter;

import org.paranora.mapstruct.starter.core.java.metadata.entity.Meta;

public abstract class AbsMetaConverter<S extends Meta,T extends Meta> implements MetaConverter<S,T> {

    @Override
    public T convert(S source, String targetClassName) {
        try {
            return convert(source,Class.forName(targetClassName));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
