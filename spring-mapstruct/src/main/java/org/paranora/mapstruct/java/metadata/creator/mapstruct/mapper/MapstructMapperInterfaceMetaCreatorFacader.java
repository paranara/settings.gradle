package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.creator.AbsInterfaceMetaCreatorFacader;
import org.paranora.mapstruct.java.metadata.creator.factory.InterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public class MapstructMapperInterfaceMetaCreatorFacader extends AbsInterfaceMetaCreatorFacader<ClassMeta,InterfaceMeta> {

    @Override
    protected InterfaceMetaCreatorFactory<ClassMeta, InterfaceMeta> defaultFactory() {
        return new MapstructMapperInterfaceMetaCreatorFactory();
    }

}
