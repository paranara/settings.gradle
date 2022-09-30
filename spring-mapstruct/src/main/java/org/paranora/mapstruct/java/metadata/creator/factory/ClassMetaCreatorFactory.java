package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.ClassMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

import java.util.List;


public interface ClassMetaCreatorFactory<S extends Object, TP extends TypeMeta>
        extends TypeMetaCreatorFactory<S, ClassMeta, TP, ClassMetaCreator> {
    List<FieldMetaCreatorFactory<S, TP>> fieldMetaCreatorFactorys();
}
