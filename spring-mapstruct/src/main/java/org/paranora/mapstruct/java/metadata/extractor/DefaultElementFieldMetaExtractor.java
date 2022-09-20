package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultElementFieldMetaExtractor implements ElementFieldMetaExtractor {

    protected ElementAnnotationMetaExtractor annotationDefinitionExtractor;

    public DefaultElementFieldMetaExtractor(ElementAnnotationMetaExtractor annotationDefinitionExtractor) {
        defaultAnnotationDefinitionExtractor(annotationDefinitionExtractor);
    }

    public DefaultElementFieldMetaExtractor() {
        init();
    }

    public void init() {
        defaultAnnotationDefinitionExtractor(defaultAnnotationDefinitionExtractor());
    }

    protected ElementAnnotationMetaExtractor defaultAnnotationDefinitionExtractor() {
        return new DefaultElementAnnotationMetaExtractor();
    }

    protected void defaultAnnotationDefinitionExtractor(ElementAnnotationMetaExtractor annotationDefinitionExtractor) {
        this.annotationDefinitionExtractor = annotationDefinitionExtractor;
    }

    @Override
    public List<FieldMeta> extract(TypeElement source) {
        List<FieldMeta> definitions = new ArrayList<>();
        source.getEnclosedElements().stream().filter(e -> ElementKind.FIELD == e.getKind()).forEach(e -> {
            definitions.add(FieldMeta.builder()
                    .name(e.getSimpleName().toString())
                    .typeName(TypeName.get(e.asType()))
                    .annotations(annotationDefinitionExtractor.extract((VariableElement) e).stream().collect(
                            Collectors.toMap(AnnotationMeta::getName
                                    , am -> am
                                    , (key1, key2) -> key2
                            )))
                    .build());
        });
        return definitions;
    }
}
