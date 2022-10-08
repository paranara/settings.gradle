package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructAnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapstructDecoratorClassDelegateFieldAnnotationMetaCreator extends AbsMapstructAnnotationMetaCreator<InterfaceMeta, ClassMeta> {


    @Override
    public List<AnnotationMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        List<AnnotationMeta> metas=new ArrayList<>();
        return Arrays.asList(
                AnnotationMeta.builder()
                        .name(Autowired.class.getSimpleName())
                        .typeName(TypeName.get(Autowired.class))
                        .packageName(Autowired.class.getPackage().getName())
                        .build()
                , AnnotationMeta.builder()
                        .name(Qualifier.class.getSimpleName())
                        .typeName(TypeName.get(Qualifier.class))
                        .packageName(Qualifier.class.getPackage().getName())
                        .fields(Arrays.asList(
                                        AnnotationFieldMeta.builder()
                                                .name("value")
                                                .typeName(TypeName.get(String.class))
                                                .value(ValueMeta.builder()
                                                        .name("value")
                                                        .typeName(TypeName.get(String.class))
                                                        .value("delegate")
                                                        .build())
                                                .build())
                                .stream()
                                .collect(Collectors.toMap(AnnotationFieldMeta::getName, o -> o, (key1, key2) -> key2)))
                        .build());
    }
}