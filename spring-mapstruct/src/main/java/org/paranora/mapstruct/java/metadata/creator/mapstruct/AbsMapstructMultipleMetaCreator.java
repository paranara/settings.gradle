package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import org.paranora.mapstruct.java.metadata.creator.MetaMultipleCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

public abstract class AbsMapstructMultipleMetaCreator<S extends TypeMeta,E extends Meta,TP extends TypeMeta>
        extends AbsMapstructMetaCreator<S,E,TP>
        implements MetaMultipleCreator<S, E, TP> {

    @Override
    public E create(S source, TP parent, Class<?> clasz) {
        return null;
    }
}
