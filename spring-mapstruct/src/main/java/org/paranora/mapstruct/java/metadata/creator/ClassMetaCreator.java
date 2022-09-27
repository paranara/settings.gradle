package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;

public interface ClassMetaCreator<S extends Object, TP extends Meta> extends TypeMetaCreator<S, ClassMeta, TP> {
}
