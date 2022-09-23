package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbsAnnotationMetaExtractor<S extends Object, AOT extends Object> extends AbsMetaMultipleExtractor<S, AnnotationMeta> implements AnnotationMetaExtractor<S> {

    public AnnotationMeta extract(S source, AOT annotationObj) {
        AnnotationMeta definition = new AnnotationMeta();
        definition.setFields(extractFields(source, annotationObj));
        return definition;
    }

    protected abstract Map<String, AnnotationFieldMeta> extractFields(S source, AOT annotationObj);

    public abstract List<AOT> getAnnotations(S source);

    @Override
    protected List<MetaExtractor> initSubExtractors() {
        this.subExtractors = new ArrayList<>();
        return this.subExtractors;
    }

    @Override
    protected <TT> TT extractHandler(S source) {
        List<AnnotationMeta> metas = new ArrayList<>();
        List<AOT> annotations = getAnnotations(source);
        annotations.forEach(a -> {
            metas.add(extract(source, a));
        });
        return (TT) metas;
    }

}
