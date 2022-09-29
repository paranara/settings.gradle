package org.paranora.mapstruct.java.metadata.creator.facader;

import org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator.MapstructClassMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public class MapstructClassMetaCreatorFacader extends AbsClassMetaCreatorFacader<InterfaceMeta, ClassMeta, ClassMeta, MapstructClassMetaCreatorFactory> {

    @Override
    protected MapstructClassMetaCreatorFactory defaultFactory() {
        return new MapstructClassMetaCreatorFactory();
    }
}
