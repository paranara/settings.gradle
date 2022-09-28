package org.paranora.mapstruct.java.code.generator.poet;


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
        if (null != meta && null != meta.getSuperInterfaces() && meta.getSuperInterfaces().size() > 0) {
            return meta.getSuperInterfaces()
                    .stream()
                    .map(si -> ParameterizedTypeName.get(ClassName.get(si.getPackageName(), si.getName()), si.getGenericTypes().toArray(new TypeName[]{})))
                    .collect(Collectors.toList());
        }
        return null;
    }

    protected List<Modifier> createModifiers(D meta) {
        return meta.getAccessLevels();
    }

    protected List<AnnotationSpec> createAnnotations(D meta) {
        if (null != meta && null != meta.getAnnotations() && meta.getAnnotations().size() > 0) {
            return meta.getAnnotations().stream().map(a -> annotationJavapoetGenerator.create(a)).collect(Collectors.toList());
        }
        return null;
    }

    protected List<MethodSpec> createMethods(D meta) {
        if (null != meta && null != meta.getMethods()&&meta.getMethods().size()>0) {
            return meta.getMethods().stream().map(m -> methodJavapoetGenerator.create(m)).collect(Collectors.toList());
        }
        return null;
    }

    protected abstract TypeSpec.Builder createTypeSpec(D meta);

    protected TypeSpec.Builder initTypeSpec(D meta) {
        TypeSpec.Builder builder = createTypeSpec(meta);
        List<TypeName> superinterfaces = createSuperinterfaces(meta);
        if (null != superinterfaces) {
            builder.addSuperinterfaces(createSuperinterfaces(meta));
        }

        List<Modifier> modifiers = createModifiers(meta);
        if (null != modifiers && modifiers.size() > 0) {
            builder.addModifiers(modifiers.toArray(new Modifier[]{}));
        }

        List<AnnotationSpec> annotations = createAnnotations(meta);
        if (null != annotations && annotations.size() > 0) {
            builder.addAnnotations(annotations);
        }

        List<MethodSpec> methods = createMethods(meta);
        if (null != methods && methods.size() > 0) {
            builder.addMethods(methods);
        }
        return builder;
    }

}
