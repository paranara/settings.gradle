package org.paranora.mapstruct.java.code.poet;


import com.squareup.javapoet.*;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbsJavapoetTypeGenerator<D extends TypeMeta, T extends TypeSpec> extends AbsJavapoetGenerator<D, T> {

    protected AnnotationJavapoetGenerator annotationJavapoetGenerator;
    protected MethodJavapoetGenerator methodJavapoetGenerator;

    public AbsJavapoetTypeGenerator() {
        init();
    }

    public AbsJavapoetTypeGenerator(AnnotationJavapoetGenerator annotationJavapoetGenerator, MethodJavapoetGenerator methodJavapoetGenerator) {
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
    public T create(D meta) {
        return (T) initTypeSpec(meta).build();
    }

    protected List<TypeName> createSuperinterfaces(D meta) {
        return meta.getSuperInterfaces()
                .stream()
                .map(si -> ParameterizedTypeName.get(ClassName.get(si.getPackageName(), si.getName()), si.getGenericTypes().toArray(new TypeName[]{})))
                .collect(Collectors.toList());
    }

    protected List<Modifier> createModifiers(D meta) {
        return meta.getAccessLevels();
    }

    protected List<AnnotationSpec> createAnnotations(D meta) {
        return meta.getAnnotations().stream().map(a -> annotationJavapoetGenerator.create(a)).collect(Collectors.toList());
    }

    protected List<MethodSpec> createMethods(D meta) {
        return meta.getMethods().stream().map(m -> methodJavapoetGenerator.create(m)).collect(Collectors.toList());
    }

    protected abstract TypeSpec.Builder createTypeSpec(D meta);

    protected TypeSpec.Builder initTypeSpec(D meta) {
        return createTypeSpec(meta)
                .addSuperinterfaces(createSuperinterfaces(meta))
                .addModifiers(createModifiers(meta).toArray(new Modifier[]{}))
                .addAnnotations(createAnnotations(meta))
                .addMethods(createMethods(meta));
    }

}
