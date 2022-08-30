package org.paranora.mapstruct.starter.core.java.generator.definition.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.FieldDefinition;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

public class DefaultElementFieldDefinitionExtractor implements ElementFieldDefinitionExtractor{

    protected ElementAnnotationDefinitionExtractor annotationDefinitionExtractor;

    public DefaultElementFieldDefinitionExtractor() {
        init();
    }

    public void init() {
        this.annotationDefinitionExtractor = defaultAnnotationDefinitionExtractor();
    }

    protected ElementAnnotationDefinitionExtractor defaultAnnotationDefinitionExtractor() {
        return new DefaultElementAnnotationDefinitionExtractor();
    }

    @Override
    public List<FieldDefinition> extract(TypeElement source) {
        List<FieldDefinition> definitions=new ArrayList<>();
        source.getEnclosedElements().stream().filter(e -> ElementKind.FIELD == e.getKind()).forEach(e -> {
            definitions.add(FieldDefinition.builder()
                            .name(e.getSimpleName().toString())
                            .typeName(TypeName.get(e.asType()))
                            .annotations(annotationDefinitionExtractor.extract((VariableElement)e))
                    .build());
        });
        return definitions;
    }
}
