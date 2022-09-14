package org.paranora.mapstruct.java.metadata.converter;

import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import java.util.List;
import java.util.Optional;

public class DefaultMapstructMapperAnnotationMetaConverter extends AbsMapstructAnnotationMetaConverter implements MapstructMapperAnnotationMetaConverter {

    @Override
    public List<AnnotationMeta> convert(ClassMeta source, Class targetClass) {
        AnnotationMeta annotationMeta = findAnnotationMeta(source, PMapper.class);
        if (null != annotationMeta) {
            return annotationMetaConverter().convert(annotationMeta, targetClass);
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
