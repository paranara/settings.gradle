package org.paranora.mapstruct.java.metadata.converter;

import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import java.util.ArrayList;
import java.util.List;


public class DefaultMapstructMappingAnnotationMetaConverter extends AbsMapstructAnnotationMetaConverter implements MapstructMappingAnnotationMetaConverter {

    @Override
    public List<AnnotationMeta> convert(ClassMeta source, Class targetClass) {
        List<AnnotationMeta> annotationMetas=new ArrayList<>();

        return annotationMetas;
    }
}
