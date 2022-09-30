package org.paranora.mapstruct.java.metadata.creator.facader;

import org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator.MapstructDecoratorClassMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public class MapstructDecoratorClassMetaCreatorFacader extends AbsClassMetaCreatorFacader<InterfaceMeta, ClassMeta, ClassMeta, MapstructDecoratorClassMetaCreatorFactory> {

    @Override
    protected MapstructDecoratorClassMetaCreatorFactory defaultFactory() {
        return new MapstructDecoratorClassMetaCreatorFactory();
    }
}
