package org.paranora.mapstruct.java.metadata.extractor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;

import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.stream.Collectors;

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
    protected List<MetaExtractor> createSubExtractors() {
        List<MetaExtractor> metaExtractors = super.createSubExtractors();
        metaExtractors.add(new DefaultElementFieldMetaExtractor());
        return metaExtractors;
    }

    @Override
    protected void extractSubsHandler(TypeElement source,MetaExtractor metaExtractor, ClassMeta parent) {
        if (metaExtractor instanceof ElementAnnotationMetaExtractor) {
            parent.setAnnotations(((ElementAnnotationMetaExtractor)metaExtractor).extracts(source));
        } else if (metaExtractor instanceof ElementFieldMetaExtractor) {
            parent.setFields(((ElementFieldMetaExtractor)metaExtractor).extracts(source).stream().collect(Collectors.toMap(FieldMeta::getName, o -> o, (key1, key2) -> key2)));
        } else{

        }
    }
}
