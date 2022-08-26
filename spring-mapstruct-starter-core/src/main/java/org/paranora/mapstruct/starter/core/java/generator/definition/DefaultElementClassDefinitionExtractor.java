package org.paranora.mapstruct.starter.core.java.generator.definition;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.ClassDefinition;

import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;


public class DefaultElementClassDefinitionExtractor implements ElementClassDefinitionExtractor {

    protected ElementAnnotationDefinitionExtractor annotationDefinitionExtractor;
    protected ElementFieldDefinitionExtractor fieldDefinitionExtractor;

    public DefaultElementClassDefinitionExtractor() {
        init();
    }

    public void init() {
        this.annotationDefinitionExtractor = defaultAnnotationDefinitionExtractor();
        this.fieldDefinitionExtractor = defaultFieldDefinitionExtractor();
    }

    protected ElementAnnotationDefinitionExtractor defaultAnnotationDefinitionExtractor() {
        return new DefaultElementAnnotationDefinitionExtractor();
    }

    protected ElementFieldDefinitionExtractor defaultFieldDefinitionExtractor() {
        return new DefaultElementFieldDefinitionExtractor();
    }

    protected String readPackageName(TypeElement source) {
        if (source instanceof TypeElement) {
            TypeElement typeElement = (TypeElement) source;
            String name = typeElement.getQualifiedName().toString();
            return name.substring(0, name.lastIndexOf('.'));
        }
        return null;
    }

    @Override
    public List<ClassDefinition> extract(TypeElement source) {
        if (null == source) return null;
        List<ClassDefinition> definitions = new ArrayList<>();
        ClassDefinition definition = ClassDefinition.builder()
                .packageName(readPackageName(source))
                .name(source.getSimpleName().toString())
                .annotations(annotationDefinitionExtractor.extract(source))
                .fields(fieldDefinitionExtractor.extract(source))
                .build();
        definitions.add(definition);
        return definitions;
    }

}
