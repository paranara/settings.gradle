package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructAnnotationMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import java.util.ArrayList;
import java.util.List;

public class MapstructClassDelegateFieldAnnotationMetaCreator extends AbsMapstructAnnotationMetaCreator<InterfaceMeta,ClassMeta> {


    @Override
    public List<AnnotationMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        List<AnnotationMeta> metas=new ArrayList<>();

        return metas;
    }
}