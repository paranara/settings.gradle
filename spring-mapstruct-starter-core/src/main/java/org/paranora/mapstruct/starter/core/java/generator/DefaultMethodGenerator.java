package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.MethodSpec;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MethodDefinition;

import javax.lang.model.element.Modifier;
import java.util.stream.Collectors;


public class DefaultMethodGenerator extends AbsJavapoetGenerator<MethodDefinition, MethodSpec> implements MethodJavapoetGenerator {

    protected ParameterJavapoetGenerator parameterJavapoetGenerator;
    protected AnnotationJavapoetGenerator annotationJavapoetGenerator;

    public DefaultMethodGenerator() {
        this.init();
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
    public MethodSpec create(MethodDefinition definition) {
        MethodSpec.Builder builder= MethodSpec.methodBuilder(definition.getName())
                .addAnnotations(definition.getAnnotations().stream().map(a -> annotationJavapoetGenerator.create(a)).collect(Collectors.toList()))
                .addParameters(definition.getParameters().stream().map(p -> parameterJavapoetGenerator.create(p)).collect(Collectors.toList()))
                .addModifiers(definition.getAccessLevels())
                .returns(definition.getReturnType());
        definition.getAnnotationClazs().forEach(ac->builder.addAnnotation(ac));
        return builder.build();
    }
}
