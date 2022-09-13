package org.paranora.mapstruct.java.metadata.converter;

import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

public abstract class AbsMapstructAnnotationMetaConverter extends AbsMetaConverter<ClassMeta, AnnotationMeta> implements AnnotationMetaConverter<ClassMeta> {

    protected AnnotationMetaConverter<AnnotationMeta> annotationMetaConverter;

    public AbsMapstructAnnotationMetaConverter() {
        init();
    }

    protected void init() {
        defaultAnnotationMetaConverter(defaultAnnotationMetaConverter());
    }

    protected void defaultAnnotationMetaConverter(AnnotationMetaConverter<AnnotationMeta> converter) {
        this.annotationMetaConverter = converter;
    }

    protected AnnotationMetaConverter<AnnotationMeta>annotationMetaConverter() {
        return this.annotationMetaConverter;
    }

    protected AnnotationMetaConverter<AnnotationMeta> defaultAnnotationMetaConverter() {
        return new DefaultAnnotationMetaConverter();
    }

}
