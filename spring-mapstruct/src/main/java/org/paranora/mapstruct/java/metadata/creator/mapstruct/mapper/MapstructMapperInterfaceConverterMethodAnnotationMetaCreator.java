package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.java.metadata.converter.AnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.converter.DefaultAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.entity.*;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
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
    public List<AnnotationMeta> creates(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        List<AnnotationMeta> metas = source.getFields()
                .values()
                .stream()
                .map(f -> {
                    AnnotationMeta meta = null;
                    Optional<AnnotationMeta> pmapping = f.getAnnotations().stream().filter(v -> v.getName().equalsIgnoreCase(PMapping.class.getSimpleName())).findFirst();
                    if (pmapping.isPresent()) {
                        if (!pmapping.get().containsField(PMapping.NEST) || false == pmapping.get().field(PMapping.NEST).value().value(boolean.class)) {
                            meta = annotationMetaConverter.convert(pmapping.get(), Mapping.class);
                        } else {
                            meta = pmapping.get();
                            if (!meta.containsField(PMapping.SOURCETYPE)) {
                                AnnotationFieldMeta souceType = AnnotationFieldMeta.builder()
                                        .name(PMapping.SOURCETYPE)
                                        .packageName(f.getPackageName())
                                        .typeName(f.getTypeName())
                                        .value(ValueMeta.builder()
                                                .typeName(f.getTypeName())
                                                .value(f.getTypeName())
                                                .build())
                                        .build();
                                meta.setField(souceType);
                            }
                        }
                        if (!meta.containsField(PMapping.TARGET)) {
                            AnnotationFieldMeta targetField = AnnotationFieldMeta.builder()
                                    .name(PMapping.TARGET)
                                    .packageName(String.class.getPackage().getName())
                                    .typeName(TypeName.get(String.class))
                                    .value(ValueMeta.builder()
                                            .typeName(TypeName.get(String.class))
                                            .value(f.getName())
                                            .build())
                                    .build();
                            meta.setField(targetField);
                        }
                        if (!meta.containsField(PMapping.EXPRESSION)) {
                            AnnotationFieldMeta sourceField = AnnotationFieldMeta.builder()
                                    .name(PMapping.SOURCE)
                                    .packageName(String.class.getPackage().getName())
                                    .typeName(TypeName.get(String.class))
                                    .value(ValueMeta.builder()
                                            .typeName(TypeName.get(String.class))
                                            .value(f.getName())
                                            .build())
                                    .build();
                            meta.setField(sourceField);
                        }
                    }
                    return meta;
                })
                .filter(m -> null != m && m.getFields().size() > 0)
                .collect(Collectors.toList());
        return metas;
    }
}
