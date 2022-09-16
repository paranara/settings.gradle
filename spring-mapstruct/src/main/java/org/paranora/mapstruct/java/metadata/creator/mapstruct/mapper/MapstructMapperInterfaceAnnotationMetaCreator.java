package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.metadata.converter.AnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.converter.DefaultAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.Arrays;
import java.util.List;

public class MapstructMapperInterfaceAnnotationMetaCreator extends AbsMapstructInterfaceAnnotationMetaCreator {

    protected AnnotationMetaConverter annotationMetaConverter;

    protected MapstructMapperInterfaceAnnotationMetaCreator() {

    }

    protected void init() {
        defaultMapstructMapperInterfaceAnnotationMetaConverter(defaultMapstructMapperInterfaceAnnotationMetaConverter());
    }

    protected AnnotationMetaConverter defaultMapstructMapperInterfaceAnnotationMetaConverter() {
        return new DefaultAnnotationMetaConverter();
    }

    public void defaultMapstructMapperInterfaceAnnotationMetaConverter(AnnotationMetaConverter annotationMetaConverter) {
        this.annotationMetaConverter = annotationMetaConverter;
    }

    @Override
    public List<AnnotationMeta> create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        AnnotationMeta convertMapperAnnotations = this.annotationMetaConverter.convert(source.getAnnotations()
                        .stream()
                        .filter(a -> a.getName().equalsIgnoreCase(PMapper.class.getSimpleName()))
                        .findFirst().get()
                , Mapper.class);

        AnnotationMeta decorated = AnnotationMeta.builder()
                .name(DecoratedWith.class.getSimpleName())
                .packageName(DecoratedWith.class.getPackage().getName())
                .fields(Arrays.asList(
                        AnnotationFieldMeta.builder()
                                .name("value")
                                .typeName(TypeName.get(Class.class))
                                .value(String.format("%s.%sDecorator.class", parent.getPackageName(), parent.getName()))
                                .build()
                ))
                .build();

        AnnotationMeta mapper = AnnotationMeta.builder()
                .name(Mapper.class.getSimpleName())
                .packageName(Mapper.class.getPackage().getName())
                .fields(Arrays.asList(
                        AnnotationFieldMeta.builder()
                                .name("componentModel")
                                .typeName(TypeName.get(String.class))
                                .value("spring")
                                .build()
                        , AnnotationFieldMeta.builder()
                                .name("nullValueCheckStrategy")
                                .typeName(TypeName.get(NullValueCheckStrategy.class))
                                .value(NullValueCheckStrategy.ALWAYS)
                                .build()
                        , AnnotationFieldMeta.builder()
                                .name("nullValueMappingStrategy")
                                .typeName(TypeName.get(NullValueMappingStrategy.class))
                                .value(NullValueMappingStrategy.RETURN_DEFAULT)
                                .build()
                ))
                .build();



        return Arrays.asList(mapper, decorated);
    }


}