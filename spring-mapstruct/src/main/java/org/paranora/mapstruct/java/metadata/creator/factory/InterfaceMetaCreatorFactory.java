package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;


public interface InterfaceMetaCreatorFactory<S extends Object, T extends TypeMeta, TP extends TypeMeta>
        extends TypeMetaCreatorFactory<S, T, TP, InterfaceMetaCreator> {
}
