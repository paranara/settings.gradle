package org.paranora.mapstruct.java.metadata.creator.facader;

import org.paranora.mapstruct.java.metadata.creator.MetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.MetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.BaseMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;

public abstract class AbsMetaCreatorFacader<S extends Object, T extends BaseMeta, TP extends BaseMeta, F extends MetaCreatorFactory>
        implements MetaCreator<S, T, TP> {

    protected F factory;

    public AbsMetaCreatorFacader() {
        this.init();
    }

    protected void init() {
        this.factory = defaultFactory();
    }

    protected abstract F defaultFactory();

    @Override
    public T create(S source, TP parent, Class<?> clasz) {
        T meta = createRoot(source, null, clasz);
       meta=createRootAnnotations(source, (TP) meta,clasz);
        return (T) meta;
    }

    protected abstract T createRoot(S source, TP parent, Class<?> clasz);

    protected T createRootAnnotations(S source, TP parent, Class<?> clasz){
        List<AnnotationMeta> annotations = createRootAnnotationMetas(source,parent, clasz);
        if (null != annotations && annotations.size() > 0) {
            parent.setAnnotations(annotations);
        }
        return (T) parent;
    }

    protected List<AnnotationMeta> createRootAnnotationMetas(S source, TP parent, Class<?> clasz) {
        return factory.annotationCreator().creates(source, parent, clasz);
    }

}
