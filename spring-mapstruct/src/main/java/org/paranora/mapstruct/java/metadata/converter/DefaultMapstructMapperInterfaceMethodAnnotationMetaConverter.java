package org.paranora.mapstruct.java.metadata.converter;


import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import java.util.ArrayList;
import java.util.List;


public class DefaultMapstructMapperInterfaceMethodAnnotationMetaConverter extends AbsMapstructAnnotationMetaConverter implements MapstructMapperInterfaceMethodAnnotationMetaConverter {

    @Override
    public List<AnnotationMeta> convert(ClassMeta source) {
        List<AnnotationMeta> metas=new ArrayList<>();
        return metas;
    }
}
