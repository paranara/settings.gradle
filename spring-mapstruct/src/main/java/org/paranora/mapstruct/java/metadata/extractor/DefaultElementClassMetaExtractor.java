package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class DefaultElementClassMetaExtractor extends AbsElementTypeMetaExtractor {

    @Override
    protected ClassMeta extractHandler(TypeElement source) {
        if (null == source) return null;
        ClassMeta meta = ClassMeta.builder()
                .packageName(readPackageName(source))
                .name(source.getSimpleName().toString())
                .typeName(TypeName.get(source.asType()))
                .build();
        return meta;
    }

    @Override
    protected void createSubExtractors() {
        super.createSubExtractors();
        this.subExtractors.add(new DefaultElementFieldMetaExtractor());
    }

    @Override
    protected void extractSubHandler(TypeElement source, MetaExtractor metaExtractor, ClassMeta meta) {
        if (metaExtractor instanceof ElementAnnotationMetaExtractor) {
            meta.setAnnotations(((ElementAnnotationMetaExtractor) metaExtractor).extracts(source));
        } else if (metaExtractor instanceof ElementFieldMetaExtractor) {
            source.getEnclosedElements()
                    .stream()
                    .filter(e -> ElementKind.FIELD == e.getKind())
                    .forEach(e -> {
                        meta.getFields().put(e.getSimpleName().toString(),((ElementFieldMetaExtractor) metaExtractor).extract((VariableElement) e));
                    });
        } else {

        }
    }
}
