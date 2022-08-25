package org.paranora.mapstruct.starter.core.java.generator.definition;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DefaultReflectMPAnnotationDefinitionCreator extends AbsAnnotationDefinitionCreator<AccessibleObject, Annotation> {

    @Override
    protected List<AnnotationFieldDefinition> createFields(AccessibleObject source, Annotation annotationObj) {
        List<AnnotationFieldDefinition> annotationFieldDefinitions = new ArrayList<>();
        for (Method method : annotationObj.annotationType().getDeclaredMethods()) {
            try {
                annotationFieldDefinitions.add(AnnotationFieldDefinition.builder()
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
    public Annotation getAnnotation(AccessibleObject source, String annotaionClassName) {
        Optional<Annotation> opt = Arrays.stream(source.getAnnotations()).filter(a -> a.getClass().getName().equalsIgnoreCase(annotaionClassName)).findFirst();
        return opt.isPresent() ? opt.get() : null;
    }

    @Override
    public Annotation getAnnotation(AccessibleObject source, Class annotaionClass) {
        return source.getAnnotation(annotaionClass);
    }

}
