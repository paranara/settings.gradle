package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbsMetaMultipleExtractor<S extends Object, D extends Meta> extends AbsMetaExtractor<S, D> implements ElementMetaMultipleExtractor<S,D> {

    protected List<MetaExtractor> subExtractors = new ArrayList<>();

    protected void init() {
        this.subExtractors = createSubExtractors();
    }

    protected List<MetaExtractor> createSubExtractors() {
        return Arrays.asList(new DefaultElementAnnotationMetaExtractor());
    }

    protected void extractsSubs(S source, List<D> parent) {
        if (null == this.subExtractors || this.subExtractors.size() < 1 || null == parent || parent.size() < 1) return;
        this.subExtractors.forEach(m -> {
            parent.forEach(p -> {
                extractSubsHandler(source,m, p);
            });
        });
    }

    @Override
    protected D extractHandler(S source) {
        return null;
    }

    protected abstract List<D> extractsHandler(S source);

    @Override
    public List<D> extracts(S source) {
        List<D> metas = extractsHandler(source);
        extractsSubs(source, metas);
        return metas;
    }
}
