package org.paranora.mapstruct.starter.processor;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.annotations.MPMapper;
import org.paranora.mapstruct.starter.core.annotations.MPMapping;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;


@SupportedAnnotationTypes(MapstructStarterProcessor.MPMapperAnnotationName)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MapstructStarterProcessor extends AbsProcessor {

    public static final String MPMapperAnnotationName = "org.paranora.mapstruct.starter.core.annotations.MPMapper";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        print("entry.");

        processPresentAnnotation(annotations, roundEnvironment, MPMapper.class, element -> {
            TypeMirror typeMirror = element.asType();
            ElementKind kind = element.getKind();
            print("typeMirror = " + typeMirror.toString());
            print("kind = " + kind.toString());

            if (ElementKind.CLASS == kind) {
                element.getEnclosedElements().stream().filter(e -> ElementKind.FIELD == e.getKind()).forEach(e -> {
                    VariableElement variableElement = (VariableElement) e;
                    print("class %s field : %s ", typeMirror.toString(), variableElement.getSimpleName());
                    MPMapping mpMapping[] = variableElement.getAnnotationsByType(MPMapping.class);
                    if (null != mpMapping) {
                        Arrays.stream(mpMapping).forEach(mapping -> {

                            Arrays.stream(MPMapping.class.getDeclaredFields()).forEach(f->{
                                try {
                                    print("class %s field : %s , annotation : %s , [%s : %s]", typeMirror.toString(), variableElement.getSimpleName(), MPMapping.class.getSimpleName(), f.getName(),f.get(f));
                                } catch (IllegalAccessException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });

                            print("class %s field : %s , annotation : %s , [targerClass : %s]", typeMirror.toString()
                                    , variableElement.getSimpleName()
                                    , MPMapping.class.getSimpleName()
                                    , getAnnotationClassNameAttribute(mapping, (annotation -> ((MPMapping) annotation).targetClass())));
                            print("class %s field : %s , annotation : %s , [targer : %s]", typeMirror.toString(), variableElement.getSimpleName(), MPMapping.class.getSimpleName(), mapping.target());
                        });
                    }

//                    variableElement.getAnnotationMirrors().stream().filter(annotationMirror -> MPMapping.class.getName().equalsIgnoreCase(annotationMirror.getAnnotationType().toString())).forEach(annotationMirror -> {
//                        Class annotationClass= null;
//                        try {
//                            annotationClass = Class.forName(annotationMirror.getAnnotationType().toString());
//                        } catch (ClassNotFoundException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                        MPMapping annotation= (MPMapping) variableElement.getAnnotation(annotationClass);
//                        print("class %s field : %s , annotation : %s", typeMirror.toString(), variableElement.getSimpleName(), annotation.toString());
//                    });
                });
            }

            print("process");
            MPMapper mpMapper = element.getAnnotation(MPMapper.class);
            String classFullName = element.asType().toString();
            String className = element.getSimpleName().toString();

            Element enclosingElement = element.getEnclosingElement();
            String enclosingQualifiedname;
            if (enclosingElement instanceof PackageElement) {
                enclosingQualifiedname = ((PackageElement) enclosingElement).getQualifiedName().toString();
            } else {
                enclosingQualifiedname = ((TypeElement) enclosingElement).getQualifiedName().toString();
            }
            print("mpMapper [name : %s] ", mpMapper.name());
            print("className = " + className);
            print("classFullName = " + classFullName);
            print("enclosingQualifiedname = " + enclosingQualifiedname);
        });

        return false;
    }


    private String getAnnotationClassNameAttribute(Annotation annotation, Function<Annotation, Class<?>> readAction) {
        String result = null;
        try {
            Class<?> classType = readAction.apply(annotation);
            result=classType.getName();
        } catch (MirroredTypeException mte) {
            TypeMirror typeMirror = mte.getTypeMirror();
            result = typeMirror.toString();
        }
        return result;
    }


    @Override
    protected String prefix() {
        return "MapstructProcessor.process entry. ";
    }

}
