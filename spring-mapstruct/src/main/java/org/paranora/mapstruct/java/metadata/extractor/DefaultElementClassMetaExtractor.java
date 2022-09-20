package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DefaultElementClassMetaExtractor implements ElementClassMetaExtractor {

    protected ElementAnnotationMetaExtractor annotationDefinitionExtractor;
    protected ElementFieldMetaExtractor fieldDefinitionExtractor;


    protected Elements elementUtils;
    protected Types typesUtils;

    public DefaultElementClassMetaExtractor(Types typesUtils, Elements elementUtils) {
        this();
        this.typesUtils = typesUtils;
        this.elementUtils = elementUtils;
    }

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
        return new DefaultElementFieldMetaExtractor(this.annotationDefinitionExtractor);
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
                .typeName(TypeName.get(source.asType()))
                .annotations(annotationDefinitionExtractor.extract(source).stream().collect(
                        Collectors.toMap(AnnotationMeta::getName
                                , am -> am
                                , (key1, key2) -> key2
                        )))
                .fields(fieldDefinitionExtractor.extract(source))
                .build();
        definitions.add(definition);
        return definitions;
    }

}
