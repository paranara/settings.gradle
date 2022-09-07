package org.paranora.mapstruct.starter.core.java.generator.poet;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.starter.core.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.starter.core.java.metadata.entity.ValueMeta;

public class DefaultAnnotationGenerator extends AbsJavapoetGenerator<AnnotationMeta, AnnotationSpec> implements AnnotationJavapoetGenerator {

    @Override
    public AnnotationSpec create(AnnotationMeta definition) {
        if (null == definition) return null;
        AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get(definition.getPackageName(), definition.getName()));
        if (null != definition.getFields() && definition.getFields().size() > 0) {
            definition.getFields().stream().forEach(f -> {
                builder.addMember(f.getName(), this.valueJavapoetGenerator.create(ValueMeta.builder().typeName(f.getTypeName()).value(f.getValue()).build()));
            });
        }
        return builder.build();
    }

}
