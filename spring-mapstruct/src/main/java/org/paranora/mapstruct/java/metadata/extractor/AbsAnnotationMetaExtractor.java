package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsAnnotationMetaExtractor<S extends Object, AOT extends Object> implements AnnotationMetaExtractor<S> {

    public AnnotationMeta extract(S source, AOT annotationObj) {
        AnnotationMeta definition = new AnnotationMeta();
        definition.setFields(extractFields(source, annotationObj));
        return definition;
    }

    protected abstract List<AnnotationFieldMeta> extractFields(S source, AOT annotationObj);

    public abstract List<AOT> getAnnotations(S source);

    @Override
    public List<AnnotationMeta> extract(S source) {
        List<AnnotationMeta> definitions = new ArrayList<>();
        List<AOT> annotations = getAnnotations(source);
        annotations.forEach(a -> {
            definitions.add(extract(source, a));
        });
        return definitions;
    }
}
