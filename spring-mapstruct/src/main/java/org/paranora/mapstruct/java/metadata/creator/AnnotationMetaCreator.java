package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public interface AnnotationMetaCreator<S extends Object, TP extends Meta> extends MetaCreator<S, List<AnnotationMeta>, TP> {

}
