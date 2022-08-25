package org.paranora.mapstruct.starter.core.java.generator.definition;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import javax.lang.model.element.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultElementAnnotationDefinitionCreator extends AbsAnnotationDefinitionCreator<Element, AnnotationMirror> {

    protected AnnotationValueVisitor annotationValueVisitor;

    public DefaultElementAnnotationDefinitionCreator(AnnotationValueVisitor annotationValueVisitor) {
        this.annotationValueVisitor = annotationValueVisitor;
    }

    public DefaultElementAnnotationDefinitionCreator() {
        init();
    }

    protected void init() {
        this.annotationValueVisitor = new CustomAnnotationValueVisitor();
    }

    @Override
    protected List<AnnotationFieldDefinition> createFields(Element source, AnnotationMirror annotationObj) {
        List<AnnotationFieldDefinition> annotationFieldDefinitions = new ArrayList<>();
        annotationObj.getElementValues().entrySet().stream().forEach(entry -> {
            TypeName returnType = TypeName.get(entry.getKey().getReturnType());
            String key = entry.getKey().getSimpleName().toString();
            Object value = entry.getValue().accept(this.annotationValueVisitor, null);
            annotationFieldDefinitions.add(AnnotationFieldDefinition.builder()
                    .name(key)
                    .typeName(returnType)
                    .value(value)
                    .build());
        });
        return annotationFieldDefinitions;
    }

    @Override
    public AnnotationMirror getAnnotation(Element source, String annotaionClassName) {
        Optional<? extends AnnotationMirror> opt = source.getAnnotationMirrors()
                .stream()
                .filter(t -> t.getAnnotationType().toString().equalsIgnoreCase(annotaionClassName))
                .findFirst();
        return opt.isPresent() ? opt.get() : null;
    }

}
