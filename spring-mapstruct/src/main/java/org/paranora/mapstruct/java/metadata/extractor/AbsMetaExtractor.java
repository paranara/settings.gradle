package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbsMetaExtractor<S extends Object, D extends Meta> implements MetaExtractor<S, D> {

    protected List<MetaExtractor> subExtractors = new ArrayList<>();

    public AbsMetaExtractor(){
        init();
    }

    protected void init() {
        this.subExtractors = createSubExtractors();
    }

    protected List<MetaExtractor> createSubExtractors() {
        this.subExtractors = new ArrayList<>();
        this.subExtractors.add(new DefaultElementAnnotationMetaExtractor());
        return this.subExtractors;
    }

    protected void extractSubs(S source, D parent) {
        if (null == this.subExtractors || this.subExtractors.size() < 1 || null == parent) return;
        this.subExtractors.forEach(m -> {
            extractSubsHandler(source,m, parent);
        });
    }

    protected  void extractSubsHandler(S source,MetaExtractor metaExtractor, D parent){}

    protected abstract D extractHandler(S source);

    @Override
    public D extract(S source) {
        D meta = extractHandler(source);
        extractSubs(source, meta);
        return meta;
    }
}
