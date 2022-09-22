package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.creator.factory.InterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

import java.util.stream.Collectors;

public abstract class AbsInterfaceMetaCreatorFacader<S extends Object, TP extends Meta> implements InterfaceMetaCreator<S, TP> {

    protected InterfaceMetaCreatorFactory<S, TP> factory;

    public AbsInterfaceMetaCreatorFacader() {
        this.init();
    }

    protected void init() {
        this.factory = defaultFactory();
    }

    protected abstract InterfaceMetaCreatorFactory<S, TP> defaultFactory();

    @Override
    public InterfaceMeta create(S source, TP parent, Class<?> clasz) {
        InterfaceMeta meta = factory.InterfaceCreator().create(source, null, clasz);
        TP interfaceMeta = (TP) meta;
        meta.setAnnotations(factory.annotationCreator().creates(source, interfaceMeta, clasz));

        meta.setMethods(factory.methodCreatorFactorys()
                .stream()
                .map(mf -> {
                    MethodMeta methodMeta = mf.methodCreator().create(source, interfaceMeta, clasz);
                    methodMeta.setParameters(mf.parameterCreator().creates(source, interfaceMeta, clasz));
                    methodMeta.setAnnotations(mf.annotationCreator().creates(source, interfaceMeta, clasz));
                    return methodMeta;
                })
                .collect(Collectors.toList())
        );
        return meta;
    }
}
