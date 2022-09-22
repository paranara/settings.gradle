package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.Mapping;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.java.metadata.converter.AnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.converter.DefaultAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapstructMapperInterfaceConverterMethodAnnotationMetaCreator extends AbsMapstructInterfaceMethodAnnotationMetaCreator {

    protected AnnotationMetaConverter annotationMetaConverter;

    protected MapstructMapperInterfaceConverterMethodAnnotationMetaCreator() {

    }

    protected void init() {
        defaultAnnotationMetaConverter(defaultAnnotationMetaConverter());
    }

    protected AnnotationMetaConverter defaultAnnotationMetaConverter() {
        return new DefaultAnnotationMetaConverter();
    }

    public void defaultAnnotationMetaConverter(AnnotationMetaConverter annotationMetaConverter) {
        this.annotationMetaConverter = annotationMetaConverter;
    }


    @Override
    public List<AnnotationMeta> creates(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        List<AnnotationMeta> metas = source.getFields()
                .values()
                .stream()
                .map(f -> {
                    AnnotationMeta meta = null;
                    Optional<AnnotationMeta> pmapping = f.getAnnotations().stream().filter(v -> v.getName().equalsIgnoreCase(PMapping.class.getSimpleName())).findFirst();
                    if (pmapping.isPresent()) {
                        meta = annotationMetaConverter.convert(pmapping.get(), Mapping.class);
                    } else {
                        meta = AnnotationMeta.builder()
                                .name(Mapping.class.getSimpleName())
                                .packageName(Mapping.class.getPackage().getName())
                                .fields(Arrays.asList(AnnotationFieldMeta.builder()
                                                        .name("target")
                                                        .packageName(String.class.getPackage().getName())
                                                        .typeName(TypeName.get(String.class))
                                                        .value(f.getName())
                                                        .build()
                                                )
                                                .stream()
                                                .collect(Collectors.toMap(AnnotationFieldMeta::getName, afm -> afm, (key1, key2) -> key2))
                                )
                                .build();
                    }
                    AnnotationFieldMeta fieldMeta = AnnotationFieldMeta.builder()
                            .name("source")
                            .packageName(String.class.getPackage().getName())
                            .typeName(TypeName.get(String.class))
                            .value(f.getName())
                            .build();
                    meta.getFields().put(fieldMeta.getName(), fieldMeta);
                    return meta;
                })
                .collect(Collectors.toList());

        return metas;
    }
}
