package org.paranora.mapstruct.starter.core.java.generator;

import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import java.util.List;

public abstract class AbsAnnotationDefinitionCreator<S extends Object, AOT extends Object> implements AnnotationDefinitionCreator<S> {

    @Override
    public AnnotationDefinition create(S source, String annotaionClassName) {
        AnnotationDefinition annotationDefinition = new AnnotationDefinition();
        AOT annotation = getAnnotation(source, annotaionClassName);
        if (null != annotation) {
            annotationDefinition.setFields(createFields(source,annotation));
        }
        return annotationDefinition;
    }

    @Override
    public AnnotationDefinition create(S source, Class annotaionClass) {
        AnnotationDefinition annotationDefinition = new AnnotationDefinition();
        AOT annotation = getAnnotation(source, annotaionClass);
        if (null != annotation) {
            annotationDefinition.setFields(createFields(source,annotation));
        }
        return annotationDefinition;
    }

    protected abstract List<AnnotationFieldDefinition> createFields(S source,AOT annotationObj);

    public abstract AOT getAnnotation(S source, String annotaionClassName);

    public AOT getAnnotation(S source, Class annotaionClass) {
        return getAnnotation(source, annotaionClass.getName());
    }
}
