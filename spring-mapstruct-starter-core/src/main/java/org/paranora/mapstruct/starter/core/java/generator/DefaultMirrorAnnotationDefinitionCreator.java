package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class DefaultMirrorAnnotationDefinitionCreator implements AnnotationDefinitionCreator<AnnotationMirror> {


    @Override
    public AnnotationDefinition create(AnnotationMirror arg) {
        AnnotationDefinition annotationDefinition = new AnnotationDefinition();
        annotationDefinition.setFields(new ArrayList<>());

        arg.getElementValues().entrySet().stream().forEach(en -> {
            annotationDefinition.getFields().add(create(arg,en));
        });

        return annotationDefinition;
    }

    protected AnnotationFieldDefinition create(AnnotationMirror annotationMirror, Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry) {
        return AnnotationFieldDefinition.builder()
                .name(entry.getKey().getSimpleName().toString())
                .build();
    }
}
