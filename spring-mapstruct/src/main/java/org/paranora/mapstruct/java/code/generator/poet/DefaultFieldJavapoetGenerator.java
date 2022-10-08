package org.paranora.mapstruct.java.code.generator.poet;


import com.squareup.javapoet.FieldSpec;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;

import javax.lang.model.element.Modifier;
import java.util.stream.Collectors;

public class DefaultFieldJavapoetGenerator extends AbsJavapoetGenerator<FieldMeta, FieldSpec> implements FieldJavapoetGenerator {

    protected AnnotationJavapoetGenerator annotationJavapoetGenerator;

    protected AnnotationJavapoetGenerator defaultAnnotationJavapoetGenerator() {
        return new DefaultAnnotationGenerator();
    }

    public void defaultAnnotationJavapoetGenerator(AnnotationJavapoetGenerator annotationJavapoetGenerator) {
        this.annotationJavapoetGenerator = annotationJavapoetGenerator;
    }

    @Override
    public FieldSpec create(FieldMeta meta) {
        FieldSpec.Builder builder = FieldSpec.builder(meta.getTypeName(), meta.getName())
                .addModifiers(meta.getAccessLevels().toArray(new Modifier[0]))
                .addAnnotations(meta.getAnnotations().stream().map(a -> annotationJavapoetGenerator.create(a)).filter(a -> null != a).collect(Collectors.toList()));
        if (null != meta.getValue() && null != meta.getValue().getValue()) {
            builder.initializer(this.valueJavapoetGenerator.create(meta.getValue()));
        }
        return builder.build();
    }

    @Override
    protected void init() {
        super.init();
        defaultAnnotationJavapoetGenerator(defaultAnnotationJavapoetGenerator());
    }
}
