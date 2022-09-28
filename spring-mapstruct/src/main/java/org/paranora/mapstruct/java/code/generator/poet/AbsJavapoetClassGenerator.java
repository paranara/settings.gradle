package org.paranora.mapstruct.java.code.generator.poet;


import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import java.util.List;
import java.util.stream.Collectors;


public abstract class AbsJavapoetClassGenerator<D extends ClassMeta, T extends TypeSpec> extends AbsJavapoetInterfaceGenerator<D, T> {

    protected FieldJavapoetGenerator fieldJavapoetGenerator;

    protected FieldJavapoetGenerator defaultFieldJavapoetGenerator() {
        return new DefaultFieldJavapoetGenerator();
    }

    protected void defaultFieldJavapoetGenerator(FieldJavapoetGenerator fieldJavapoetGenerator) {
        this.fieldJavapoetGenerator = fieldJavapoetGenerator;
    }

    protected List<FieldSpec> createFields(D meta){
        return meta.getFields()
                .values()
                .stream()
                .map(f->this.fieldJavapoetGenerator.create(f))
                .collect(Collectors.toList());
    }

    @Override
    protected void init() {
        super.init();
        defaultFieldJavapoetGenerator(defaultFieldJavapoetGenerator());
    }

    @Override
    protected TypeSpec.Builder createTypeSpec(D meta) {
        return TypeSpec.classBuilder(meta.getName());
    }

    @Override
    protected TypeSpec.Builder initTypeSpec(D meta) {
        TypeSpec.Builder builder = super.initTypeSpec(meta);
        builder.addFields(createFields(meta));
        return builder;
    }
}
