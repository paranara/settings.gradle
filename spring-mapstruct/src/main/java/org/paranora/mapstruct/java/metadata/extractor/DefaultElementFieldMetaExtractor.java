package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;

import javax.lang.model.element.VariableElement;

public class DefaultElementFieldMetaExtractor extends AbsElementFieldMetaExtractor {

    @Override
    protected void extractSubHandler(VariableElement source, MetaExtractor metaExtractor, FieldMeta meta) {
        if (metaExtractor instanceof ElementAnnotationMetaExtractor) {
            ElementAnnotationMetaExtractor extractor = (ElementAnnotationMetaExtractor) metaExtractor;
            meta.setAnnotations(extractor.extracts(source));
        }
    }

    @Override
    protected <TT> TT extractHandler(VariableElement source) {
        FieldMeta meta = FieldMeta.builder()
                .name(source.getSimpleName().toString())
                .typeName(TypeName.get(source.asType()))
                .build();
        return (TT) meta;
    }
}
