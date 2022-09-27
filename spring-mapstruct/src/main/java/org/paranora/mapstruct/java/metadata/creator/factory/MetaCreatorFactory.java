package org.paranora.mapstruct.java.metadata.creator.factory;



import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface MetaCreatorFactory<S extends Object,T extends Meta,TP extends Meta> {
    AnnotationMetaCreator<S,TP> annotationCreator();

}
