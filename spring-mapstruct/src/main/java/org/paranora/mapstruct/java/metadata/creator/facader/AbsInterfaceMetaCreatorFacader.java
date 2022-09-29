package org.paranora.mapstruct.java.metadata.creator.facader;

import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.InterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.BaseMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbsInterfaceMetaCreatorFacader<S extends Object, T extends InterfaceMeta, TP extends BaseMeta,F extends InterfaceMetaCreatorFactory>
        extends AbsTypeMetaCreatorFacader<S, InterfaceMeta, TP,F>
        implements InterfaceMetaCreator<S,TP>  {

}
