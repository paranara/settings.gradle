package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import org.paranora.mapstruct.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructMultipleMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public abstract class AbsMapstructAnnotationMetaCreator extends AbsMapstructMultipleMetaCreator<AnnotationMeta> implements AnnotationMetaCreator<ClassMeta, InterfaceMeta> {

    public AbsMapstructAnnotationMetaCreator() {
        init();
    }

    protected void init() {

    }

}
