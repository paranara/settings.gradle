package org.paranora.mapstruct.java.metadata.creator.factory;



import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface InterfaceMetaCreatorFactory<S extends Object,TP extends Meta> extends MetaCreatorFactory<S,TP> {
    List<MethodMetaCreatorFactory<S,TP>>  methodCreatorFactorys();

    InterfaceMetaCreator<S,TP> InterfaceCreator();

}
