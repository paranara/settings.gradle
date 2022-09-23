package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsMetaExtractor<S extends Object, D extends Meta> implements MetaExtractor<S, D> {

    protected List<MetaExtractor> subExtractors = new ArrayList<>();

    public AbsMetaExtractor() {
        init();
    }

    protected void init() {
        initSubExtractors();
    }

    protected List<MetaExtractor> initSubExtractors() {
        subExtractors().add(new DefaultElementAnnotationMetaExtractor());
        return this.subExtractors;
    }

    protected List<MetaExtractor> subExtractors() {
        if (null == this.subExtractors) {
            this.subExtractors = new ArrayList<>();
        }
        return this.subExtractors;
    }

    protected void extractSubExtractor(S source, D parent) {
        if (null == this.subExtractors || this.subExtractors.size() < 1 || null == parent) return;
        this.subExtractors.forEach(m -> {
            extractSubExtractorHandler(source, m, parent);
        });
    }

    protected void extractSubExtractorHandler(S source, MetaExtractor metaExtractor, D parent) {
    }

    protected abstract <TT> TT extractHandler(S source);

    @Override
    public D extract(S source) {
        D meta = extractHandler(source);
        extractSubExtractor(source, meta);
        return meta;
    }
}
