package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.mapstruct.Mapping;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import java.util.List;
import java.util.stream.Collectors;

public class MapstructMapperInterfaceConverterMethodAnnotationMetaCreator extends AbsMapstructInterfaceMethodAnnotationMetaCreator {


    @Override
    public List<AnnotationMeta> create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        List<AnnotationMeta> metas = source.getFields()
                .stream()
                .map(f -> {
                    AnnotationMeta annotationMeta = AnnotationMeta.builder()
                            .name(Mapping.class.getSimpleName())
                            .packageName(Mapping.class.getPackage().getName())
                            .fields(f.getAnnotations().stream().filter(a -> a.getName().equals(PMapping.class.getSimpleName())).findFirst().get().getFields())
                            .build();
                    return annotationMeta;
                })
                .collect(Collectors.toList());

        return metas;
    }
}
