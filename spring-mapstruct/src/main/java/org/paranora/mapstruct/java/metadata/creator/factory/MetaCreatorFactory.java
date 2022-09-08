package org.paranora.mapstruct.java.metadata.creator.factory;



import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;

import java.util.List;

public interface MetaCreatorFactory<S extends Object> {
    List<AnnotationMetaCreator<S>> annotationCreators();
}
