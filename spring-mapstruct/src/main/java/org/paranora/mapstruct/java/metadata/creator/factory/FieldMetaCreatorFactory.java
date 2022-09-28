package org.paranora.mapstruct.java.metadata.creator.factory;


import org.paranora.mapstruct.java.metadata.creator.FieldMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

public interface FieldMetaCreatorFactory<S extends Object, TP extends Meta> extends MetaCreatorFactory<S, MethodMeta, TP> {
    FieldMetaCreator<S, TP> fIeldMetaCreator();
}
