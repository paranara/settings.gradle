package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

import java.util.List;

public interface InterfaceMetaCreatorFactory<S extends Object, T extends TypeMeta, TP extends Meta>
        extends TypeMetaCreatorFactory<S, T, TP, InterfaceMetaCreator> {
}
