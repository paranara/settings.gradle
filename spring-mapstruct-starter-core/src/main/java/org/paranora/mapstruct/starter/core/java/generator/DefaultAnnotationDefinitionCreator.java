package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class DefaultAnnotationDefinitionCreator implements AnnotationDefinitionCreator<Annotation> {

    @Override
    public AnnotationDefinition create(Annotation arg) {
        AnnotationDefinition annotationDefinition = new AnnotationDefinition();
        annotationDefinition.setFields(new ArrayList<>());
        Arrays.stream(arg.annotationType().getDeclaredMethods()).forEach(m -> {
            try {
                AnnotationFieldDefinition annotationFieldDefinition = createField(arg, m);
                if (null != annotationFieldDefinition) {
                    annotationDefinition.getFields().add(annotationFieldDefinition);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        return annotationDefinition;
    }

    protected AnnotationFieldDefinition createField(Annotation annotation, Method method) throws InvocationTargetException, IllegalAccessException {
        if (method.getReturnType() != Class.class) {
            return AnnotationFieldDefinition.builder()
                    .name(method.getName())
                    .classType(method.getReturnType())
                    .typeName(TypeName.get(method.getReturnType()))
                    .value(method.invoke(annotation, new Object[]{}))
                    .build();
        }
        return null;
    }
}
