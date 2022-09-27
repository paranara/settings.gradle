package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.FIeldMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

public interface FieldMetaCreatorFactory<S extends Object, TP extends Meta> extends MetaCreatorFactory<S, MethodMeta, TP> {
    FIeldMetaCreator<S, TP> fIeldMetaCreator();
}
