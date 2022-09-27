package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.TypeMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

import java.util.List;

public interface TypeMetaCreatorFactory<S extends Object, T extends TypeMeta, TP extends Meta, CT extends TypeMetaCreator>
        extends MetaCreatorFactory<S, T, TP> {
    List<MethodMetaCreatorFactory<S, TP>> methodCreatorFactorys();

    TypeMetaCreator typeMetaCreator();
}
