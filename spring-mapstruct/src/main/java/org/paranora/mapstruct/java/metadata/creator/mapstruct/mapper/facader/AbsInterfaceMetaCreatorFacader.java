package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper.facader;

import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.InterfaceMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.BaseMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbsInterfaceMetaCreatorFacader<S extends Object, T extends InterfaceMeta, TP extends BaseMeta,F extends InterfaceMetaCreatorFactory>
        extends AbsMetaCreatorFacader<S, InterfaceMeta, TP,F>
        implements InterfaceMetaCreator<S,TP>  {


    @Override
    public T create(S source, TP parent, Class<?> clasz) {
        T meta = (T) super.create(source, parent, clasz);
        meta.setMethods(createRootMethods(source, (TP) meta, clasz));
        return (T) meta;
    }

    @Override
    protected T createRoot(S source, TP parent, Class<?> clasz) {
        return (T) factory.InterfaceCreator().create(source, null, clasz);
    }

    protected List<MethodMeta> createRootMethods(S source, TP parent, Class<?> clasz) {
        return (List<MethodMeta>) factory.methodCreatorFactorys()
                .stream()
                .map(mf -> {
                    MethodMetaCreatorFactory f=(MethodMetaCreatorFactory)mf;
                    MethodMeta methodMeta = (MethodMeta) f.methodCreator().create(source, parent, clasz);
                    methodMeta.setParameters(f.parameterCreator().creates(source, parent, clasz));
                    methodMeta.setAnnotations(f.annotationCreator().creates(source, parent, clasz));
                    return methodMeta;
                })
                .collect(Collectors.toList());
    }
}
