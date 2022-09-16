package org.paranora.mapstruct.java.metadata.converter;

import org.paranora.mapstruct.java.metadata.entity.Meta;


public interface MetaConverter<T extends Meta> {
    T convert(T source, Class targetClass);
}
