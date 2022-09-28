package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.converter.AnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.converter.DefaultAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructAnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.merger.AnnotationMetaMerger;
import org.paranora.mapstruct.java.metadata.merger.DefaultAnnotationMetaMerger;

public abstract class AbsMapstructInterfaceAnnotationMetaCreator extends AbsMapstructAnnotationMetaCreator<ClassMeta, InterfaceMeta> {
    protected AnnotationMetaConverter annotationMetaConverter;
    protected AnnotationMetaMerger annotationMetaMerger;

    protected AbsMapstructInterfaceAnnotationMetaCreator() {

    }

    protected void init() {
        defaultAnnotationMetaConverter(defaultAnnotationMetaConverter());
        defaultAnnotationMetaMerger(defaultAnnotationMetaMerger());
    }

    protected AnnotationMetaConverter defaultAnnotationMetaConverter() {
        return new DefaultAnnotationMetaConverter();
    }

    protected AnnotationMetaMerger defaultAnnotationMetaMerger() {
        return new DefaultAnnotationMetaMerger();
    }

    public void defaultAnnotationMetaConverter(AnnotationMetaConverter annotationMetaConverter) {
        this.annotationMetaConverter = annotationMetaConverter;
    }

    protected void defaultAnnotationMetaMerger(AnnotationMetaMerger annotationMetaMerger) {
        this.annotationMetaMerger = annotationMetaMerger;
    }
}
