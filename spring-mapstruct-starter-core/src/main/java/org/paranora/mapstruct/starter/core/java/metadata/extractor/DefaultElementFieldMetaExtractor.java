package org.paranora.mapstruct.starter.core.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.metadata.entity.FieldMeta;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

public class DefaultElementFieldMetaExtractor implements ElementFieldMetaExtractor {

    protected ElementAnnotationMetaExtractor annotationDefinitionExtractor;

    public DefaultElementFieldMetaExtractor() {
        init();
    }

    public void init() {
        this.annotationDefinitionExtractor = defaultAnnotationDefinitionExtractor();
    }

    protected ElementAnnotationMetaExtractor defaultAnnotationDefinitionExtractor() {
        return new DefaultElementAnnotationMetaExtractor();
    }

    @Override
    public List<FieldMeta> extract(TypeElement source) {
        List<FieldMeta> definitions=new ArrayList<>();
        source.getEnclosedElements().stream().filter(e -> ElementKind.FIELD == e.getKind()).forEach(e -> {
            definitions.add(FieldMeta.builder()
                            .name(e.getSimpleName().toString())
                            .typeName(TypeName.get(e.asType()))
                            .annotations(annotationDefinitionExtractor.extract((VariableElement)e))
                    .build());
        });
        return definitions;
    }
}
