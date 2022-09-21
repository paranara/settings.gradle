package org.paranora.mapstruct.java.generator.poet;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import javax.lang.model.element.Modifier;
import java.util.stream.Collectors;

public class DefaultInterfaceGenerator extends AbsJavapoetGenerator<InterfaceMeta, TypeSpec> implements InterfaceJavapoetGenerator {

    protected AnnotationJavapoetGenerator annotationJavapoetGenerator;
    protected MethodJavapoetGenerator methodJavapoetGenerator;

    public DefaultInterfaceGenerator() {
        init();
    }

    public DefaultInterfaceGenerator(AnnotationJavapoetGenerator annotationJavapoetGenerator,MethodJavapoetGenerator methodJavapoetGenerator){
        defaultAnnotationJavapoetGenerator(annotationJavapoetGenerator);
        defaultMethodJavapoetGenerator(methodJavapoetGenerator);
    }

    protected void init() {
        this.annotationJavapoetGenerator = defaultAnnotationJavapoetGenerator();
        this.methodJavapoetGenerator = defaultMethodJavapoetGenerator();
    }

    protected AnnotationJavapoetGenerator defaultAnnotationJavapoetGenerator() {
        return new DefaultAnnotationGenerator();
    }

    public void defaultAnnotationJavapoetGenerator(AnnotationJavapoetGenerator annotationJavapoetGenerator) {
        this.annotationJavapoetGenerator = annotationJavapoetGenerator;
    }

    protected MethodJavapoetGenerator defaultMethodJavapoetGenerator() {
        return new DefaultMethodGenerator();
    }

    protected void defaultMethodJavapoetGenerator(MethodJavapoetGenerator methodJavapoetGenerator) {
        this.methodJavapoetGenerator = methodJavapoetGenerator;
    }

    @Override
    public TypeSpec create(InterfaceMeta definition) {
        TypeSpec interfaceSpec = TypeSpec.interfaceBuilder(definition.getName())
                .addSuperinterfaces(definition.getSuperInterfaces()
                        .stream()
                        .map(si -> ParameterizedTypeName.get(ClassName.get(si.getPackageName(), si.getName()), si.getGenericTypes().toArray(new TypeName[]{})))
                        .collect(Collectors.toList()))
                .addModifiers(definition.getAccessLevels().toArray(new Modifier[]{}))
                .addAnnotations(definition.getAnnotations().values().stream().map(a -> annotationJavapoetGenerator.create(a)).collect(Collectors.toList()))
                .addMethods(definition.getMethods().stream().map(m -> methodJavapoetGenerator.create(m)).collect(Collectors.toList()))
                .build();
        return interfaceSpec;
    }
}
