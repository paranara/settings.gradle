package org.paranora.mapstruct.java.code.generator.poet;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import org.paranora.mapstruct.java.metadata.entity.MethodMeta;

import java.util.stream.Collectors;


public class DefaultMethodGenerator extends AbsJavapoetGenerator<MethodMeta, MethodSpec> implements MethodJavapoetGenerator {

    protected ParameterJavapoetGenerator parameterJavapoetGenerator;
    protected AnnotationJavapoetGenerator annotationJavapoetGenerator;

    public DefaultMethodGenerator() {
        this.init();
    }

    public DefaultMethodGenerator(ParameterJavapoetGenerator parameterJavapoetGenerator, AnnotationJavapoetGenerator annotationJavapoetGenerator) {
        defaultAnnotationJavapoetGenerator(annotationJavapoetGenerator);
        defaultParameterJavapoetGenerator(parameterJavapoetGenerator);
    }

    public void init() {
        this.parameterJavapoetGenerator = defaultParameterJavapoetGenerator();
        this.annotationJavapoetGenerator = defaultAnnotationJavapoetGenerator();
    }

    protected ParameterJavapoetGenerator defaultParameterJavapoetGenerator() {
        return new DefaultParameterGenerator();
    }

    public void defaultParameterJavapoetGenerator(ParameterJavapoetGenerator parameterJavapoetGenerator) {
        this.parameterJavapoetGenerator = parameterJavapoetGenerator;
    }

    protected AnnotationJavapoetGenerator defaultAnnotationJavapoetGenerator() {
        return new DefaultAnnotationGenerator();
    }

    public void defaultAnnotationJavapoetGenerator(AnnotationJavapoetGenerator annotationJavapoetGenerator) {
        this.annotationJavapoetGenerator = annotationJavapoetGenerator;
    }

    @Override
    public MethodSpec create(MethodMeta meta) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(meta.getName())
                .addAnnotations(meta.getAnnotations().stream().map(a -> annotationJavapoetGenerator.create(a)).filter(a -> null != a).collect(Collectors.toList()))
                .addParameters(meta.getParameters().stream().map(p -> parameterJavapoetGenerator.create(p)).filter(p -> null != p).collect(Collectors.toList()))
                .addModifiers(meta.getAccessLevels())
                .returns(meta.getReturnType());
        if (null != meta.getValue() && meta.getValue() instanceof CodeBlock) {
            builder.addStatement((CodeBlock) meta.getValue());
        }
        meta.getAnnotationClazs().forEach(ac -> builder.addAnnotation(ac));
        return builder.build();
    }
}
