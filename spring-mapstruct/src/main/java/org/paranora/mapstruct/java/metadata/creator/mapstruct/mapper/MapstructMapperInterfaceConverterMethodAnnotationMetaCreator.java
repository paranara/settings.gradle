package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.Mapping;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.java.metadata.converter.AnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.converter.DefaultAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.creator.merger.AnnotationMetaMerger;
import org.paranora.mapstruct.java.metadata.creator.merger.DefaultAnnotationMetaMerger;
import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    public List<AnnotationMeta> create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        List<AnnotationMeta> metas = source.getFields()
                .stream()
                .map(f -> {
                    Optional<AnnotationMeta> pmapping = f.getAnnotations().values().stream().filter(v -> v.getName().equalsIgnoreCase(PMapping.class.getSimpleName())).findFirst();
                    if (pmapping.isPresent()) {
                        AnnotationMeta meta = annotationMetaConverter.convert(pmapping.get(), Mapping.class);
                        meta.getFields().put("source",AnnotationFieldMeta.builder()
                                .name("source")
                                .packageName(String.class.getPackage().getName())
                                .typeName(TypeName.get(String.class))
                                .value(f.getName())
                                .build());
                        return meta;
                    } else {
                        AnnotationMeta annotationMeta = AnnotationMeta.builder()
                                .name(Mapping.class.getSimpleName())
                                .packageName(Mapping.class.getPackage().getName())
                                .fields(new HashMap<String, AnnotationFieldMeta>() {
                                    {
                                        put("target", AnnotationFieldMeta.builder()
                                                .name("target")
                                                .packageName(String.class.getPackage().getName())
                                                .typeName(TypeName.get(String.class))
                                                .value(f.getName())
                                                .build());

                                        put("source", AnnotationFieldMeta.builder()
                                                .name("source")
                                                .packageName(String.class.getPackage().getName())
                                                .typeName(TypeName.get(String.class))
                                                .value(f.getName())
                                                .build());
                                    }
                                })
                                .build();
                        return annotationMeta;
                    }
                })
                .collect(Collectors.toList());

        return metas;
    }
}
