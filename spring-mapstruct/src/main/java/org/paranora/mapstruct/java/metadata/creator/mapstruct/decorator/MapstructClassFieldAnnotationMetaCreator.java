package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructAnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;
import java.util.List;

public class MapstructClassFieldAnnotationMetaCreator extends AbsMapstructAnnotationMetaCreator<InterfaceMeta,ClassMeta> {


    @Override
    public List<AnnotationMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        return Arrays.asList(
                AnnotationMeta.builder()
                        .name(Autowired.class.getSimpleName())
                        .typeName(TypeName.get(Autowired.class))
                        .packageName(Autowired.class.getPackage().getName())
                        .build()
                , AnnotationMeta.builder()
                        .name(Lazy.class.getSimpleName())
                        .typeName(TypeName.get(Lazy.class))
                        .packageName(Lazy.class.getPackage().getName())
                        .build());
    }
}