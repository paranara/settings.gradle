package org.paranora.mapstruct.java.metadata.converter;

import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class DefaultAnnotationMetaConverter extends AbsMetaConverter<AnnotationMeta, AnnotationMeta> implements AnnotationMetaConverter {

    @Override
    public AnnotationMeta convert(AnnotationMeta source, Class targetClass) {
        AnnotationMeta result = new AnnotationMeta();
        result.setPackageName(targetClass.getPackage().getName());
        result.setName(targetClass.getSimpleName());
        Arrays.stream(targetClass.getDeclaredMethods()).forEach(m -> {
            Optional<AnnotationFieldMeta> opt = source.getFields().stream().filter(a -> a.getName().equalsIgnoreCase(m.getName())).findFirst();
            if (opt.isPresent()) {
                result.getFields().add(AnnotationFieldMeta.builder()
                        .name(opt.get().getName())
                        .typeName(opt.get().getTypeName())
                        .value(opt.get().getValue())
                        .annotations(new ArrayList<>(opt.get().getAnnotations()))
                        .build());
            }
        });
        return result;
    }
}
