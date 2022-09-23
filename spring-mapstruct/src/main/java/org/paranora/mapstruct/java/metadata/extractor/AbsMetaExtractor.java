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
        createSubExtractors();
    }
    protected void createSubExtractors() {
        if (null == this.subExtractors) {
            this.subExtractors = new ArrayList<>();
        }
        this.subExtractors.add(new DefaultElementAnnotationMetaExtractor());
    }

    protected void extractSub(S source, D meta) {
        if (null == this.subExtractors || this.subExtractors.size() < 1 || null == meta) return;
        this.subExtractors.forEach(m -> {
            extractSubHandler(source, m, meta);
        });
    }

    protected void extractSubHandler(S source, MetaExtractor metaExtractor, D parent) {}

    protected abstract <TT> TT extractHandler(S source);

    @Override
    public D extract(S source) {
        D meta = extractHandler(source);
        extractSub(source, meta);
        return meta;
    }
}
