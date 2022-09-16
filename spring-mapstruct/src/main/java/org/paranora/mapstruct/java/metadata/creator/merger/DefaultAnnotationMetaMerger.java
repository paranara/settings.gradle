package org.paranora.mapstruct.java.metadata.creator.merger;

import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;

import java.util.ArrayList;
import java.util.Optional;

public class DefaultAnnotationMetaMerger implements AnnotationMetaMerger {

    @Override
    public AnnotationMeta merge(AnnotationMeta source, AnnotationMeta target) {
        AnnotationMeta meta = AnnotationMeta.builder().build();
        meta.setPackageName(target.getPackageName());
        meta.setName(target.getName());

        source.getFields()
                .forEach(f -> {
                    Optional<AnnotationFieldMeta> opt = target.getFields().stream().filter(tf -> tf.getName().equalsIgnoreCase(f.getName()) && tf.getTypeName().equals(f.getTypeName())).findFirst();
                    if (opt.isPresent()) {
                        meta.getFields().add(AnnotationFieldMeta.builder()
                                .name(opt.get().getName())
                                .typeName(opt.get().getTypeName())
                                .value(opt.get().getValue())
                                .annotations(new ArrayList<>(opt.get().getAnnotations()))
                                .build());
                    }
                });

        return meta;
    }
}
