package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.ExecutableElement;
import java.util.ArrayList;
import java.util.Map;

public class DefaultMirrorAnnotationDefinitionCreator implements AnnotationDefinitionCreator<AnnotationMirror> {

    protected AnnotationValueVisitor annotationValueVisitor;

    public DefaultMirrorAnnotationDefinitionCreator(AnnotationValueVisitor annotationValueVisitor) {
        this.annotationValueVisitor = annotationValueVisitor;
    }

    public DefaultMirrorAnnotationDefinitionCreator() {
        init();
    }

    protected void init() {
        this.annotationValueVisitor = new CustomAnnotationValueVisitor();
    }

    @Override
    public AnnotationDefinition create(AnnotationMirror arg) {
        AnnotationDefinition annotationDefinition = new AnnotationDefinition();
        annotationDefinition.setFields(new ArrayList<>());
        arg.getElementValues().entrySet().stream().forEach(en -> {
            annotationDefinition.getFields().add(create(arg, en));
        });
        return annotationDefinition;
    }

    protected AnnotationFieldDefinition create(AnnotationMirror annotationMirror, Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry) {
        TypeName returnType = TypeName.get(entry.getKey().getReturnType());
        String key = entry.getKey().getSimpleName().toString();
        Object value = entry.getValue().accept(this.annotationValueVisitor, null);
        return AnnotationFieldDefinition.builder()
                .name(key)
                .typeName(returnType)
                .value(value)
                .build();
    }
}
