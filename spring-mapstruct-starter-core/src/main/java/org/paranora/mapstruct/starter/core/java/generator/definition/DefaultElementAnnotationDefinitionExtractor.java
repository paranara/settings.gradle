package org.paranora.mapstruct.starter.core.java.generator.definition;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.AnnotationFieldDefinition;

import javax.lang.model.element.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultElementAnnotationDefinitionExtractor extends AbsAnnotationDefinitionExtractor<Element, AnnotationMirror> implements ElementAnnotationDefinitionExtractor {

    protected AnnotationValueVisitor annotationValueVisitor;

    public DefaultElementAnnotationDefinitionExtractor(AnnotationValueVisitor annotationValueVisitor) {
        this.annotationValueVisitor = annotationValueVisitor;
    }

    public DefaultElementAnnotationDefinitionExtractor() {
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
    public List<AnnotationMirror> getAnnotations(Element source) {
        return source.getAnnotationMirrors()
                .stream()
                .collect(Collectors.toList());
    }

}
