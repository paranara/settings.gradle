package org.paranora.mapstruct.starter.processor;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
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
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
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
            processClassFields(element, variableElement -> {
                print("class %s field : %s ", typeMirror.toString(), variableElement.getSimpleName());
                Optional<? extends AnnotationMirror> annotationMirror = getAnnotationMirror(variableElement, MPMapping.class);
                if (annotationMirror.isPresent()) {
                    processAnnotationMirrorFields(annotationMirror.get(), en -> {
                        print(String.format("key : %s , type : %s , value : %s "
                                , en.getKey().getSimpleName().toString()
                                , TypeName.get(en.getKey().getReturnType())
                                , en.getValue().getValue()));
                       if (TypeName.get(en.getKey().getReturnType()).toString().equals(new Object(){}.getClass().getName())) {
                            print(" is class type by paranora , name : %s", en.getKey().getSimpleName());
                        }
                        TypeMirror typeMirrorA = en.getKey().asType();
                        Element elementA = typesUtils.asElement(typeMirrorA);

                    });
                }
            });
            print("process");
        });
        return false;
    }


    public boolean processA(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        print("entry.");
        processPresentAnnotation(annotations, roundEnvironment, MPMapper.class, element -> {
            TypeMirror typeMirror = element.asType();
            processClassFields(element, variableElement -> {
                print("class %s field : %s ", typeMirror.toString(), variableElement.getSimpleName());
                MPMapping annotation = variableElement.getAnnotation(MPMapping.class);
                if (null != annotation) {
                    Arrays.stream(annotation.annotationType().getDeclaredMethods()).forEach(m -> {
                        TypeName typeName = TypeName.get(m.getReturnType());
                        Class<?> returnType = m.getReturnType();
                        String messageFormat = "%s, %s type : %s , name : %s, class : %s , value : %s , returnType: %s .";
                        String methodReturnTypeLevel = "base";
                        if (typeName.isPrimitive()) {
                            methodReturnTypeLevel = "base";
                        } else {
                            if (typeName instanceof ClassName) {
                                methodReturnTypeLevel = "class";
                            } else if (typeName instanceof ArrayTypeName) {
                                methodReturnTypeLevel = "array";
                            } else {
                            }
                        }
                        Object v = null;
                        try {
                            v = m.invoke(annotation, new Object[]{});
                        } catch (IllegalAccessException e) {
                            print("IllegalAccessException");
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            print("InvocationTargetException");
                            e.printStackTrace();
                        } catch (MirroredTypeException mte) {
                            print("MirroredTypeException");
                            TypeName tn = ClassName.get(mte.getTypeMirror());
                            if (tn.equals(TypeName.get(void.class))) {
                                v = null;
                            } else {
                                v = tn;
                            }
                        }

                        print(String.format(messageFormat
                                , MPMapping.class.getSimpleName()
                                , methodReturnTypeLevel
                                , typeName
                                , m.getName()
                                , typeName.getClass()
                                , v
                                , returnType));
                    });
                }

                print("process 00000");


//                MPMapping mpMapping[] = variableElement.getAnnotationsByType(MPMapping.class);
//                if (null != mpMapping) {
//                    Arrays.stream(mpMapping).forEach(mapping -> {
//                        Arrays.stream(MPMapping.class.getDeclaredFields()).forEach(f -> {
//                            try {
//                                print("class %s field : %s , annotation : %s , [%s : %s]", typeMirror.toString(), variableElement.getSimpleName(), MPMapping.class.getSimpleName(), f.getName(), f.get(f));
//                            } catch (IllegalAccessException ex) {
//                                throw new RuntimeException(ex);
//                            }
//                        });
//
//                        print("class %s field : %s , annotation : %s , [targerClass : %s]", typeMirror.toString()
//                                , variableElement.getSimpleName()
//                                , MPMapping.class.getSimpleName()
//                                , getAnnotationClassNameAttribute(mapping, (annotation -> ((MPMapping) annotation).targetClass())));
//                        print("class %s field : %s , annotation : %s , [targer : %s]", typeMirror.toString(), variableElement.getSimpleName(), MPMapping.class.getSimpleName(), mapping.target());
//                    });
//                }

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

//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
//        print("entry.");
//
//        for (TypeElement typeElement : annotations) {
//            if (isPresentAnnotation(typeElement, MPMapper.class.getName())) {
//                TypeMirror typeMirror = typeElement.asType();
//                print("process 1 " + typeElement.getKind());
//                for (Element elementA : roundEnvironment.getElementsAnnotatedWith(typeElement)) {
//                    print("process 1 " + elementA.getKind());
//                    if (ElementKind.CLASS == elementA.getKind()) {
//                        for (Element element : elementA.getEnclosedElements()) {
//                            print("process 1 " + element.getKind());
//                            if (ElementKind.FIELD == element.getKind()) {
//                                VariableElement variableElement = (VariableElement) element;
//                                Annotation annotation = variableElement.getAnnotation(MPMapping.class);
//                                if (null != annotation) {
//                                    for (Method m : annotation.annotationType().getDeclaredMethods()) {
//                                        TypeName typeName = TypeName.get(m.getReturnType());
//                                        Class<?> returnType = m.getReturnType();
//                                        String messageFormat = "%s, %s type : %s , name : %s, class : %s , value : %s , returnType: %s .";
//                                        String methodReturnTypeLevel = "base";
//                                        if (typeName.isPrimitive()) {
//                                            methodReturnTypeLevel = "base";
//                                        } else {
//                                            if (typeName instanceof ClassName) {
//                                                methodReturnTypeLevel = "class";
//                                            } else if (typeName instanceof ArrayTypeName) {
//                                                methodReturnTypeLevel = "array";
//                                            } else {
//                                            }
//                                        }
//                                        Object v = null;
//                                        try {
//                                            v = m.invoke(annotation, null);
//                                        } catch (MirroredTypeException mte) {
//                                            print("MirroredTypeException");
//                                            TypeName tn = ClassName.get(mte.getTypeMirror());
//                                            if (tn.equals(TypeName.get(void.class))) {
//                                                v = null;
//                                            } else {
//                                                v = tn;
//                                            }
//                                        } catch (IllegalAccessException e) {
//                                            print("IllegalAccessException");
//                                            e.printStackTrace();
//                                        } catch (InvocationTargetException e) {
//                                            print("InvocationTargetException");
//                                            e.printStackTrace();
//                                        }
//
//                                        print(String.format(messageFormat
//                                                , MPMapping.class.getSimpleName()
//                                                , methodReturnTypeLevel
//                                                , typeName
//                                                , m.getName()
//                                                , typeName.getClass()
//                                                , v
//                                                , returnType));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//        return false;
//    }

    private String getAnnotationClassNameAttribute(Annotation annotation, Function<Annotation, Class<?>> readAction) {
        String result = null;
        try {
            Class<?> classType = readAction.apply(annotation);
            result = classType.getName();
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
