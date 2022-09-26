package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper.facader;

import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.InterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbsInterfaceMetaCreatorFacader<S extends Object, T extends InterfaceMeta, TP extends Meta> implements InterfaceMetaCreator<S, TP> {

    protected InterfaceMetaCreatorFactory<S, TP> factory;

    public AbsInterfaceMetaCreatorFacader() {
        this.init();
    }

    protected void init() {
        this.factory = defaultFactory();
    }

    protected abstract InterfaceMetaCreatorFactory<S, TP> defaultFactory();

    @Override
    public T create(S source, TP parent, Class<?> clasz) {
        T meta = createRoot(source, null, clasz);
        meta.setAnnotations(createRootAnnotationMetas(source, (TP) meta, clasz));
        meta.setMethods(createRootMethods(source, (TP) meta,clasz));
        return (T) meta;
    }

    protected T createRoot(S source, TP parent, Class<?> clasz) {
        return (T) factory.InterfaceCreator().create(source, null, clasz);
    }

    protected List<AnnotationMeta> createRootAnnotationMetas(S source, TP parent, Class<?> clasz) {
        return factory.annotationCreator().creates(source, parent, clasz);
    }

    protected List<MethodMeta> createRootMethods(S source, TP parent, Class<?> clasz) {
        return factory.methodCreatorFactorys()
                .stream()
                .map(mf -> {
                    MethodMeta methodMeta = mf.methodCreator().create(source, parent, clasz);
                    methodMeta.setParameters(mf.parameterCreator().creates(source, parent, clasz));
                    methodMeta.setAnnotations(mf.annotationCreator().creates(source, parent, clasz));
                    return methodMeta;
                })
                .collect(Collectors.toList());
    }
}
