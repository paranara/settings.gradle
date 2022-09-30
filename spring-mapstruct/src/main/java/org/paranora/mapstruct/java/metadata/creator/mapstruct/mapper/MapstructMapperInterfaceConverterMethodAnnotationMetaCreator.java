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
                    if (pmapping.isPresent()
                            && (!pmapping.get().getFields().containsKey(PMapping.NEST) || false == (boolean) pmapping.get().getFields().get(PMapping.NEST).getValue().getValue())) {
                        meta = annotationMetaConverter.convert(pmapping.get(), Mapping.class);
                        if (!meta.getFields().containsKey(PMapping.TARGET)) {
                            AnnotationFieldMeta targetField = AnnotationFieldMeta.builder()
                                    .name(PMapping.TARGET)
                                    .packageName(String.class.getPackage().getName())
                                    .typeName(TypeName.get(String.class))
                                    .value(ValueMeta.builder()
                                            .typeName(TypeName.get(String.class))
                                            .value(f.getName())
                                            .build())
                                    .build();
                            meta.getFields().put(targetField.getName(), targetField);
                        }
                        if (!meta.getFields().containsKey(PMapping.EXPRESSION)) {
                            AnnotationFieldMeta sourceField = AnnotationFieldMeta.builder()
                                    .name(PMapping.SOURCE)
                                    .packageName(String.class.getPackage().getName())
                                    .typeName(TypeName.get(String.class))
                                    .value(ValueMeta.builder()
                                            .typeName(TypeName.get(String.class))
                                            .value(f.getName())
                                            .build())
                                    .build();
                            meta.getFields().put(sourceField.getName(), sourceField);
                        }
                    }
                    return meta;
                })
                .filter(m -> null != m && m.getFields().size() > 0)
                .collect(Collectors.toList());
        return metas;
    }
}
