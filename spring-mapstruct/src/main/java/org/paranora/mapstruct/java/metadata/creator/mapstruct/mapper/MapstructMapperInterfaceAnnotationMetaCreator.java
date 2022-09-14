package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.paranora.mapstruct.java.metadata.converter.DefaultMapstructMapperAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.converter.MapstructMapperAnnotationMetaConverter;
import org.paranora.mapstruct.java.metadata.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapstructMapperInterfaceAnnotationMetaCreator extends AbsMapstructInterfaceAnnotationMetaCreator {

    protected MapstructMapperAnnotationMetaConverter annotationMetaConverter;

    protected MapstructMapperInterfaceAnnotationMetaCreator() {

    }

    protected void init() {
        defaultMapstructMapperAnnotationMetaConverter(defaultMapstructMapperAnnotationMetaConverter());
    }

    protected MapstructMapperAnnotationMetaConverter defaultMapstructMapperAnnotationMetaConverter() {
        return new DefaultMapstructMapperAnnotationMetaConverter();
    }

    public void defaultMapstructMapperAnnotationMetaConverter(MapstructMapperAnnotationMetaConverter converter) {
        this.annotationMetaConverter = converter;
    }

    @Override
    public List<AnnotationMeta> create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        List<AnnotationMeta> sourceMapperAnnotations=this.annotationMetaConverter.convert(source,Mapper.class);

        List<AnnotationMeta> resultsourceMapperAnnotations=new ArrayList<>();

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
