package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.creator.MetaMultipleCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;

public abstract class AbsMapstructMultipleMetaCreator<E extends Meta> extends AbsMapstructMetaCreator<E> implements MetaMultipleCreator<ClassMeta, E, InterfaceMeta> {

    @Override
    public E create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        return null;
    }
}
