package org.paranora.mapstruct.java.metadata.merger;

import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;

import java.util.Optional;

public class DefaultAnnotationMetaMerger implements AnnotationMetaMerger {

    @Override
    public AnnotationMeta merge(AnnotationMeta source, AnnotationMeta target) {
        if (null == target || null == target.getFields() || target.getFields().size() < 1) return source;
        AnnotationMeta meta = AnnotationMeta.builder().build();
        meta.setPackageName(target.getPackageName());
        meta.setName(target.getName());

        source.getFields()
                .values()
                .forEach(f -> {
                    Optional<AnnotationFieldMeta> opt = target.getFields()
                            .values()
                            .stream()
                            .filter(tf -> tf.getName().equalsIgnoreCase(f.getName()) && tf.getTypeName().equals(f.getTypeName()))
                            .findFirst();

                    if (opt.isPresent()) {
                        meta.getFields().put(opt.get().getName()
                                , AnnotationFieldMeta.builder()
                                        .name(opt.get().getName())
                                        .typeName(opt.get().getTypeName())
                                        .value(opt.get().getValue())
                                        .annotations(opt.get().getAnnotations())
                                        .build());
                    } else {
                        meta.getFields().put(f.getName()
                                , AnnotationFieldMeta.builder()
                                        .name(f.getName())
                                        .typeName(f.getTypeName())
                                        .value(f.getValue())
                                        .annotations(f.getAnnotations())
                                        .build());
                    }
                });

        return meta;
    }
}
