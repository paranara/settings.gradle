package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructMultipleMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

public abstract class AbsMapstructAnnotationMetaCreator<S extends TypeMeta, TP extends TypeMeta>
        extends AbsMapstructMultipleMetaCreator<S, AnnotationMeta, TP>
        implements AnnotationMetaCreator<S, TP> {

    public AbsMapstructAnnotationMetaCreator() {
        init();
    }

    protected void init() {

    }

}
