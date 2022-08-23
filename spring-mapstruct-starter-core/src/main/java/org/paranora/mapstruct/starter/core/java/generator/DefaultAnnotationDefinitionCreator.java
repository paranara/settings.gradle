package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class DefaultAnnotationDefinitionCreator implements AnnotationDefinitionCreator<Annotation> {

    @Override
    public AnnotationDefinition create(Annotation arg) {
        AnnotationDefinition annotationDefinition = new AnnotationDefinition();
        annotationDefinition.setFields(new ArrayList<>());
        Arrays.stream(arg.annotationType().getDeclaredMethods()).forEach(m -> {
            try {
                annotationDefinition.getFields().add(AnnotationFieldDefinition.builder()
                        .name(m.getName())
                        .type(m.getReturnType())
                        .typeName(TypeName.get(m.getReturnType()))
                        .value(m.invoke(arg,new  Class[0]))
                        .build());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        return annotationDefinition;
    }
}
