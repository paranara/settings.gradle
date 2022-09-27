package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper.facader;

import org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper.MapstructMapperInterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public class MapstructMapperInterfaceMetaCreatorFacader extends AbsInterfaceMetaCreatorFacader<ClassMeta, InterfaceMeta, InterfaceMeta, MapstructMapperInterfaceMetaCreatorFactory> {
    @Override
    protected MapstructMapperInterfaceMetaCreatorFactory defaultFactory() {
        return new MapstructMapperInterfaceMetaCreatorFactory();
    }


}
