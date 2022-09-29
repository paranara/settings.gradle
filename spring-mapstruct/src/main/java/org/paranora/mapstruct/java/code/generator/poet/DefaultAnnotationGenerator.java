package org.paranora.mapstruct.java.code.generator.poet;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ValueMeta;

public class DefaultAnnotationGenerator extends AbsJavapoetGenerator<AnnotationMeta, AnnotationSpec> implements AnnotationJavapoetGenerator {

    @Override
    public AnnotationSpec create(AnnotationMeta meta) {
        if (null == meta) return null;
        AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get(meta.getPackageName(), meta.getName()));
        if (null != meta.getFields() && meta.getFields().size() > 0) {
            meta.getFields().values().stream().forEach(f -> {
                builder.addMember(f.getName(), this.valueJavapoetGenerator.create(f.getValue()));
            });
        }
        return builder.build();
    }

}
