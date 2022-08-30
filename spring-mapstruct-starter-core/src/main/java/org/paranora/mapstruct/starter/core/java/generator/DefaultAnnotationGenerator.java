package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.ValueDefinition;

public class DefaultAnnotationGenerator extends AbsJavapoetGenerator<AnnotationDefinition, AnnotationSpec> implements AnnotationJavapoetGenerator {

    @Override
    public AnnotationSpec create(AnnotationDefinition definition) {
        if (null == definition) return null;
        AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get(definition.getPackageName(), definition.getName()));
        if (null != definition.getFields() && definition.getFields().size() > 0) {
            definition.getFields().stream().forEach(f -> {
                builder.addMember(f.getName(), this.codeJavapoetGenerator.create(ValueDefinition.builder().typeName(f.getTypeName()).value(f.getValue()).build()));
            });
        }
        return builder.build();
    }

}
