package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;

public class DefaultElementFieldMetaExtractor extends AbsElementFieldMetaExtractor {

    @Override
    protected void extractSubsHandler(TypeElement source, MetaExtractor metaExtractor, FieldMeta parent) {
        if (metaExtractor instanceof ElementAnnotationMetaExtractor) {
            parent.setAnnotations(((ElementAnnotationMetaExtractor)metaExtractor).extracts(source));
        }
    }

    @Override
    protected List<FieldMeta> extractsHandler(TypeElement source) {
        List<FieldMeta> definitions = new ArrayList<>();
        source.getEnclosedElements()
                .stream()
                .filter(e -> ElementKind.FIELD == e.getKind())
                .forEach(e -> {
                    definitions.add(FieldMeta.builder()
                            .name(e.getSimpleName().toString())
                            .typeName(TypeName.get(e.asType()))
                            .build());
                });
        return definitions;
    }
}
