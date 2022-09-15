package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.paranora.mapstruct.java.metadata.converter.DefaultMapstructMapperInterfaceAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.converter.MapstructMapperInterfaceAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapstructMapperInterfaceAnnotationMetaCreator extends AbsMapstructInterfaceAnnotationMetaCreator {

    protected MapstructMapperInterfaceAnnotationMetaConverter converter;

    protected MapstructMapperInterfaceAnnotationMetaCreator() {

    }

    protected void init() {
        defaultMapstructMapperInterfaceAnnotationMetaConverter(defaultMapstructMapperInterfaceAnnotationMetaConverter());
    }

    protected MapstructMapperInterfaceAnnotationMetaConverter defaultMapstructMapperInterfaceAnnotationMetaConverter() {
        return new DefaultMapstructMapperInterfaceAnnotationMetaConverter();
    }

    public void defaultMapstructMapperInterfaceAnnotationMetaConverter(MapstructMapperInterfaceAnnotationMetaConverter converters) {
        this.converter = converter;
    }

    @Override
    public List<AnnotationMeta> create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        List<AnnotationMeta> convertAnnotations = this.converter.convert(source);

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
