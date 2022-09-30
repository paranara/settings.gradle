package org.paranora.mapstruct.java.metadata.creator.facader;

import org.paranora.mapstruct.java.metadata.creator.ClassMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.ClassMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

public abstract class AbsClassMetaCreatorFacader<S extends Object, T extends ClassMeta, TP extends TypeMeta,F extends ClassMetaCreatorFactory>
        extends AbsTypeMetaCreatorFacader<S, ClassMeta, TP,F>
        implements ClassMetaCreator<S,TP> {

    @Override
    public ClassMeta create(S source, TP parent, Class<?> clasz) {
        T meta = (T) super.create(source, parent, clasz);
        return meta;
    }
}
