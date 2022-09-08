package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.CustomAnnotationValueVisitor;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;

import javax.lang.model.element.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultElementAnnotationMetaExtractor extends AbsAnnotationMetaExtractor<Element, AnnotationMirror> implements ElementAnnotationMetaExtractor {

    protected AnnotationValueVisitor annotationValueVisitor;

    public DefaultElementAnnotationMetaExtractor(AnnotationValueVisitor annotationValueVisitor) {
        this.annotationValueVisitor = annotationValueVisitor;
    }

    public DefaultElementAnnotationMetaExtractor() {
        init();
    }

    protected void init() {
        this.annotationValueVisitor = defaultAnnotationValueVisitor();
    }

    protected AnnotationValueVisitor defaultAnnotationValueVisitor() {
        return new CustomAnnotationValueVisitor();
    }

    @Override
    public AnnotationMeta extract(Element source, AnnotationMirror annotationObj) {
        Element element = annotationObj.getAnnotationType().asElement();
        AnnotationMeta definition = super.extract(source, annotationObj);
        definition.setName(element.getSimpleName().toString());
        definition.setTypeName(TypeName.get(element.asType()));
        definition.setPackageName(element.toString().substring(0, element.toString().lastIndexOf(".")));
        return definition;
    }

    @Override
    protected List<AnnotationFieldMeta> extractFields(Element source, AnnotationMirror annotationObj) {
        List<AnnotationFieldMeta> annotationFieldDefinitions = new ArrayList<>();
        annotationObj.getElementValues().entrySet().stream().forEach(entry -> {
            TypeName returnType = TypeName.get(entry.getKey().getReturnType());
            String key = entry.getKey().getSimpleName().toString();
            Object value = entry.getValue().accept(this.annotationValueVisitor, null);
            annotationFieldDefinitions.add(AnnotationFieldMeta.builder()
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
