package org.paranora.mapstruct.java.metadata.creator.facader;

import org.paranora.mapstruct.java.metadata.creator.ClassMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.factory.ClassMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.creator.factory.FieldMetaCreatorFactory;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbsClassMetaCreatorFacader<S extends Object, T extends ClassMeta, TP extends ClassMeta, F extends ClassMetaCreatorFactory>
        extends AbsTypeMetaCreatorFacader<S, ClassMeta, TP, F>
        implements ClassMetaCreator<S, TP> {

    @Override
    public T create(S source, TP parent, Class<?> clasz) {
        T meta = (T) createRoot(source, null, clasz);
        meta = (T) createRootAnnotations(source, (TP) meta, clasz);
        meta = (T) createRootFields(source, (TP) meta, clasz);
        meta = (T) createRootMethods(source, (TP) meta, clasz);
        return (T) meta;
    }

    protected T createRootFields(S source, TP parent, Class<?> clasz) {
        List<FieldMeta> fieldMetas = createRootFieldMetas(source, parent, clasz);
        if (null != fieldMetas) {
            parent.setFields(fieldMetas.stream().collect(Collectors.toMap(FieldMeta::getName, o -> o, (key1, key2) -> key2)));
        }
        return (T) parent;
    }

    protected List<FieldMeta> createRootFieldMetas(S source, TP parent, Class<?> clasz) {
        return (List<FieldMeta>) this.factory.fieldMetaCreatorFactorys()
                .stream()
                .map(f -> {
                    FieldMetaCreatorFactory ff = (FieldMetaCreatorFactory) f;
                    List<FieldMeta> fields = ff.fIeldMetaCreator().creates(source, parent, clasz);
                    if (null != fields && fields.size() > 0) {
                        fields.forEach(fi -> {
                            fi.setAnnotations(ff.annotationCreator().creates(source, parent, clasz));
                        });
                    }
                    return fields;
                })
                .collect(Collectors.toList());
    }
}
