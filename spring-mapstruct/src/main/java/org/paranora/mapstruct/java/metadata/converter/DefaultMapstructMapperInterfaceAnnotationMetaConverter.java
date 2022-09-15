package org.paranora.mapstruct.java.metadata.converter;

import org.mapstruct.Mapper;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultMapstructMapperInterfaceAnnotationMetaConverter extends AbsMapstructAnnotationMetaConverter implements MapstructMapperInterfaceAnnotationMetaConverter {

    @Override
    public List<AnnotationMeta> convert(ClassMeta source) {
        List<AnnotationMeta> metas=new ArrayList<>();

        AnnotationMeta annotationMeta = findAnnotationMeta(source, PMapper.class);
        if (null != annotationMeta) {
            metas.add(convert(annotationMeta, Mapper.class));
        }
        return null;
    }

    protected AnnotationMeta findAnnotationMeta(ClassMeta source, Class claz) {
        if (null != source && null != source.getAnnotations() && source.getAnnotations().size() > 0) {
            Optional<AnnotationMeta> opt = source.getAnnotations().stream().filter(a -> a.getName().equalsIgnoreCase(claz.getSimpleName())).findFirst();
            if (opt.isPresent()) {
                return opt.get();
            }
        }
        return null;
    }

}
