package org.paranora.mapstruct.java.metadata.creator.facader;

import org.paranora.mapstruct.java.metadata.creator.factory.MethodMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.TypeMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbsTypeMetaCreatorFacader<S extends Object, T extends TypeMeta, TP extends TypeMeta, F extends TypeMetaCreatorFactory>
        extends AbsMetaCreatorFacader<S, T, TP, F> {

    @Override
    public T create(S source, TP parent, Class<?> clasz) {
        T meta = (T) super.create(source, parent, clasz);
        List<MethodMeta> methods = createRootMethods(source, (TP) meta, clasz);
        if (null != methods && methods.size() > 0) {
            meta.setMethods(methods);
        }
        return (T) meta;
    }

    @Override
    protected T createRoot(S source, TP parent, Class<?> clasz) {
        return (T) factory.typeMetaCreator().create(source, null, clasz);
    }

    protected List<MethodMeta> createRootMethods(S source, TP parent, Class<?> clasz) {
        return (List<MethodMeta>) factory.methodCreatorFactorys()
                .stream()
                .map(mf -> {
                    MethodMetaCreatorFactory f = (MethodMetaCreatorFactory) mf;
                    MethodMeta methodMeta = (MethodMeta) f.methodCreator().create(source, parent, clasz);
                    List<ParameterMeta> parameters = f.parameterCreator().creates(source, parent, clasz);
                    if (null != parameters && parameters.size() > 0) {
                        methodMeta.setParameters(parameters);
                    }
                    List<AnnotationMeta> annotations = f.annotationCreator().creates(source, parent, clasz);
                    if (null != annotations && annotations.size() > 0) {
                        methodMeta.setAnnotations(annotations);
                    }
                    return methodMeta;
                })
                .collect(Collectors.toList());
    }
}
