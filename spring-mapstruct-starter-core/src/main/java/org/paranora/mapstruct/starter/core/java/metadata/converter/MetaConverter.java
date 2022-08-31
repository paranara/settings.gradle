package org.paranora.mapstruct.starter.core.java.metadata.converter;

import org.paranora.mapstruct.starter.core.java.metadata.entity.Meta;

public interface MetaConverter<S extends Meta,T extends Meta> {
    T convert(S source,String targetClassName);
    T convert(S source,Class targetClass);
}
