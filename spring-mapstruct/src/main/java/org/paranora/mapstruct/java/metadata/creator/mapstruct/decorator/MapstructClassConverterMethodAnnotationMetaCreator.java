package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructAnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import java.util.Arrays;
import java.util.List;

public class MapstructClassConverterMethodAnnotationMetaCreator extends AbsMapstructAnnotationMetaCreator<InterfaceMeta, ClassMeta> {

    @Override
    public List<AnnotationMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        List<AnnotationMeta> metas = Arrays.asList(
                AnnotationMeta.builder()
                        .name(Override.class.getSimpleName())
                        .typeName(TypeName.get(Override.class))
                        .packageName(Override.class.getPackage().getName())
                        .build()
        );
        return metas;
    }
}
