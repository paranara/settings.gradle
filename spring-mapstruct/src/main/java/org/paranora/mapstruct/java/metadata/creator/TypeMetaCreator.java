package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

public interface TypeMetaCreator<S extends Object,T extends TypeMeta, TP extends Meta> extends MetaCreator<S, T,TP> {
}
