package org.paranora.mapstruct.starter.core.java.metadata.extractor;

import org.paranora.mapstruct.starter.core.java.metadata.entity.ClassMeta;

import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;


public class DefaultElementClassMetaExtractor implements ElementClassMetaExtractor {

    protected ElementAnnotationMetaExtractor annotationDefinitionExtractor;
    protected ElementFieldMetaExtractor fieldDefinitionExtractor;

    public DefaultElementClassMetaExtractor() {
        init();
    }

    public void init() {
        this.annotationDefinitionExtractor = defaultAnnotationDefinitionExtractor();
        this.fieldDefinitionExtractor = defaultFieldDefinitionExtractor();
    }

    protected ElementAnnotationMetaExtractor defaultAnnotationDefinitionExtractor() {
        return new DefaultElementAnnotationMetaExtractor();
    }

    protected ElementFieldMetaExtractor defaultFieldDefinitionExtractor() {
        return new DefaultElementFieldMetaExtractor();
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
    public List<ClassMeta> extract(TypeElement source) {
        if (null == source) return null;
        List<ClassMeta> definitions = new ArrayList<>();
        ClassMeta definition = ClassMeta.builder()
                .packageName(readPackageName(source))
                .name(source.getSimpleName().toString())
                .annotations(annotationDefinitionExtractor.extract(source))
                .fields(fieldDefinitionExtractor.extract(source))
                .build();
        definitions.add(definition);
        return definitions;
    }

}
