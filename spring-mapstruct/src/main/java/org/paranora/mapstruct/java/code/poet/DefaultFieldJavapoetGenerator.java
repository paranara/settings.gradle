package org.paranora.mapstruct.java.code.poet;


import com.squareup.javapoet.FieldSpec;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;
import org.paranora.mapstruct.java.metadata.entity.ValueMeta;

import javax.lang.model.element.Modifier;

public class DefaultFieldJavapoetGenerator extends AbsJavapoetGenerator<FieldMeta, FieldSpec> implements FieldJavapoetGenerator {

    @Override
    public FieldSpec create(FieldMeta meta) {
        return FieldSpec.builder(meta.getTypeName(), meta.getName())
                .addModifiers(meta.getAccessLevels().toArray(new Modifier[0]))
                .initializer(this.valueJavapoetGenerator.create(ValueMeta.builder().typeName(meta.getTypeName()).value(meta.getValue()).build()))
                .build();
    }
}
