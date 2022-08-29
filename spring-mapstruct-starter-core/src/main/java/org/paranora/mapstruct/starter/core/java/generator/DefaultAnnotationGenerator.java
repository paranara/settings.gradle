package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.AnnotationDefinition;

public class DefaultAnnotationGenerator extends AbsJavapoetGenerator<AnnotationDefinition, AnnotationSpec> implements AnnotationJavapoetGenerator {

    @Override
    public AnnotationSpec create(AnnotationDefinition definition) {
        if (null == definition) return null;
        AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get(definition.getPackageName(), definition.getName()));
        if (null != definition.getFields() && definition.getFields().size() > 0) {
            definition.getFields().stream().forEach(f -> {
                builder.addMember(f.getName(), createCode(f.getTypeName(), f.getValue()));
            });
        }
        return builder.build();
    }

}
