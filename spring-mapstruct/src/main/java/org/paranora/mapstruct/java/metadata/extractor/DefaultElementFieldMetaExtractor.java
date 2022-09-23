package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;

public class DefaultElementFieldMetaExtractor extends AbsElementFieldMetaExtractor {

    @Override
    protected void extractSubHandler(TypeElement source, MetaExtractor metaExtractor, FieldMeta meta) {
        if (metaExtractor instanceof ElementAnnotationMetaExtractor) {
            ElementAnnotationMetaExtractor extractor = (ElementAnnotationMetaExtractor) metaExtractor;
            meta.setAnnotations(extractor.extracts(source.getEnclosedElements().stream().filter(e -> ElementKind.FIELD == e.getKind() && e.getSimpleName().contentEquals(meta.getName())).findFirst().get()));
        }
    }

    @Override
    protected <TT> TT extractHandler(TypeElement source) {
        List<FieldMeta> metas = new ArrayList<>();
        source.getEnclosedElements()
                .stream()
                .filter(e -> ElementKind.FIELD == e.getKind())
                .forEach(e -> {
                    metas.add(FieldMeta.builder()
                            .name(e.getSimpleName().toString())
                            .typeName(TypeName.get(e.asType()))
                            .build());
                });
        return (TT) metas;
    }
}
