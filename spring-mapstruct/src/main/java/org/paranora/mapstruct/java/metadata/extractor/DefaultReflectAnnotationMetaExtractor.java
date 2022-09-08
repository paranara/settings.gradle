package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultReflectAnnotationMetaExtractor extends AbsAnnotationMetaExtractor<AccessibleObject, Annotation> {

    @Override
    protected List<AnnotationFieldMeta> extractFields(AccessibleObject source, Annotation annotationObj) {
        List<AnnotationFieldMeta> annotationFieldDefinitions = new ArrayList<>();
        for (Method method : annotationObj.annotationType().getDeclaredMethods()) {
            try {
                annotationFieldDefinitions.add(AnnotationFieldMeta.builder()
                        .name(method.getName())
                        .typeName(TypeName.get(method.getReturnType()))
                        .value(method.invoke(annotationObj, new Object[]{}))
                        .build());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return annotationFieldDefinitions;
    }

    @Override
    public List<Annotation> getAnnotations(AccessibleObject source) {
        return Arrays.asList(source.getAnnotations());
    }

}
