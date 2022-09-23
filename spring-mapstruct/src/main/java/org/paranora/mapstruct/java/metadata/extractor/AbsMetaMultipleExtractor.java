package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public abstract class AbsMetaMultipleExtractor<S extends Object, D extends Meta> extends AbsMetaExtractor<S, D> implements ElementMetaMultipleExtractor<S,D> {

    @Override
    public List<D> extracts(S source) {
        List<D> metas = extractHandler(source);
        if(null!=metas&&metas.size()>0){
            metas.forEach(m->{
                extractSubExtractor(source,m);
            });
        }
        return metas;
    }
}
