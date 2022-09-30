package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapstructMapperInterfaceAnnotationMetaCreator extends AbsMapstructInterfaceAnnotationMetaCreator {


    @Override
    public List<AnnotationMeta> creates(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        AnnotationMeta convertMapper = this.annotationMetaConverter.convert(source.getAnnotations()
                        .stream()
                        .filter(a -> a.getName().equalsIgnoreCase(PMapper.class.getSimpleName()))
                        .findFirst().get()
                , Mapper.class);

        AnnotationMeta decorated = AnnotationMeta.builder()
                .name(DecoratedWith.class.getSimpleName())
                .typeName(TypeName.get(DecoratedWith.class))
                .packageName(DecoratedWith.class.getPackage().getName())
                .fields(Arrays.asList(AnnotationFieldMeta.builder()
                                .name("value")
                                .typeName(TypeName.get(Class.class))
                                .value(ValueMeta.builder()
                                        .name(createDecoratorName(source, parent, clasz))
                                        .typeName(TypeName.get(Class.class))
                                        .value(createDecoratorClassFullPathName(source, parent, clasz))
                                        .build())
                                .build())
                        .stream()
                        .collect(Collectors.toMap(AnnotationFieldMeta::getName, o -> o, (key1, key2) -> key2)))
                .build();

        AnnotationMeta mapper = AnnotationMeta.builder()
                .name(Mapper.class.getSimpleName())
                .typeName(TypeName.get(Mapper.class))
                .packageName(Mapper.class.getPackage().getName())
                .fields(Arrays.asList(
                                AnnotationFieldMeta.builder()
                                        .name(PMapper.ComponentModel)
                                        .typeName(TypeName.get(String.class))
                                        .value(ValueMeta.builder()
                                                .typeName(TypeName.get(String.class))
                                                .value("spring")
                                                .build())
                                        .build()
                                , AnnotationFieldMeta.builder()
                                        .name(PMapper.NullValueCheckStrategy)
                                        .typeName(TypeName.get(NullValueCheckStrategy.class))
                                        .value(ValueMeta.builder()
                                                .typeName(TypeName.get(NullValueCheckStrategy.class))
                                                .value(NullValueCheckStrategy.ALWAYS)
                                                .build())
                                        .build()
                                , AnnotationFieldMeta.builder()
                                        .name(PMapper.NullValueMappingStrategy)
                                        .typeName(TypeName.get(NullValueMappingStrategy.class))
                                        .value(ValueMeta.builder()
                                                .typeName(TypeName.get(NullValueMappingStrategy.class))
                                                .value(NullValueMappingStrategy.RETURN_DEFAULT)
                                                .build())
                                        .build())
                        .stream()
                        .collect(Collectors.toMap(AnnotationFieldMeta::getName, o -> o, (key1, key2) -> key2)))
                .build();

        AnnotationMeta resultMapper = annotationMetaMerger.merge(mapper, convertMapper);

        return Arrays.asList(resultMapper,decorated);
    }

    protected String createDecoratorName(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        return String.format("%sDecorator", parent.getName());
    }

    protected String createDecoratorClassFullPathName(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        return String.format("%s.%s.class", parent.getPackageName(), createDecoratorName(source, parent, clasz));
    }
}
