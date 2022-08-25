package org.paranora.mapstruct.starter.processor;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.starter.core.annotations.PMapper;
import org.paranora.mapstruct.starter.core.annotations.PMapping;
import org.paranora.mapstruct.starter.core.java.generator.AnnotationDefinitionCreator;
import org.paranora.mapstruct.starter.core.java.generator.CustomAnnotationValueVisitor;
import org.paranora.mapstruct.starter.core.java.generator.DefaultElementAnnotationDefinitionCreator;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;


@SupportedAnnotationTypes(MapstructStarterProcessor.PMapperAnnotationName)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MapstructStarterProcessor extends AbsProcessor {

    protected AnnotationDefinitionCreator annotationDefinitionCreator = new DefaultElementAnnotationDefinitionCreator();

    public static final String PMapperAnnotationName = "org.paranora.mapstruct.starter.core.annotations.PMapper";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        print("entry.");
        processPresentAnnotation(annotations, roundEnvironment, PMapper.class, element -> {
            PackageElement pkg = processingEnv.getElementUtils().getPackageOf(element);
            print("packageName : %s", pkg.getQualifiedName());
            if (element instanceof TypeElement) {
                TypeElement te = (TypeElement) element;
                String s = te.getQualifiedName().toString();
                print("package name : %s", s.substring(0, s.lastIndexOf('.')));
            }
            print("class %s field : %s ", element.asType().toString(), element.getSimpleName());
            AnnotationDefinition annotationDefinition = annotationDefinitionCreator.create(element, PMapper.class);
            annotationDefinition.getFields().stream().forEach(f -> {
                print("annotation key : %s , type : %s , value : %s ,value class : %s"
                        , f.getName()
                        , f.getTypeName()
                        , f.getValue()
                        , f.getValue().getClass());
            });
            processClassFields(element, variableElement -> {
                AnnotationDefinition mpmappingAnnotationDefinition = annotationDefinitionCreator.create(variableElement, PMapping.class);
                mpmappingAnnotationDefinition.getFields().stream().forEach(f -> {
                    Object value = f.getValue();
                    String key = f.getName();
                    print("annotation key : %s , type : %s , value : %s ,value class : %s"
                            , f.getName()
                            , f.getTypeName()
                            , value
                            , value.getClass());
                    if (value instanceof List && f.getTypeName() instanceof ArrayTypeName) {
                        List listValue = (List) value;
//                            Class<? extends List> listClass = listValue.getClass();
//                            ParameterizedType genericSuperclass = (ParameterizedType) listClass.getGenericSuperclass();
//                            Class elementType = (Class) genericSuperclass.getActualTypeArguments()[0];
                        ArrayTypeName arrayTypeName = (ArrayTypeName) f.getTypeName();
                        TypeName componentTypeName = arrayTypeName.componentType;

                        print("list component type : %s", componentTypeName.getClass());
                        listValue.forEach(e -> {
                            print("value type %s, value object : %s", e.getClass().toString(), e.toString());
                        });
                    } else if (value instanceof java.lang.Boolean) {
                        print("i found Boolean value : %s ,@ key : %s.", value, key);
                    } else if (value instanceof java.lang.String) {
                        print("i found String value : %s ,@ key : %s.", value, key);
                    } else if (value instanceof java.lang.Class) {
                        print("i found Class value : %s ,@ key : %s.", value, key);
                    } else if (value.getClass().getName().equalsIgnoreCase("com.sun.tools.javac.code.Type")) {
                        print("i found Class value : %s ,@ key : %s.", value, key);
                    } else if (value instanceof TypeMirror) {
                        print("i found Class value : %s ,@ key : %s.", value, key);
                    } else if (value instanceof VariableElement) {
                        print("i found Enum value : %s ,@ key : %s.", value, key);
                    } else {

                    }
                });
            });
            print("process");
            TypeElement elem = processingEnv.getElementUtils().getTypeElement("org.paranora.mapstruct.starter.test.entity.dto.StaffRequestDTO");
            TypeMirror tm = elem.asType();
            TypeName tn = TypeName.get(tm);
            print(tn.toString());
        });
        return false;
    }

    protected void processAnnotationMirrorFields(AnnotationMirror annotationMirror) {
        processAnnotationMirrorFields(annotationMirror, en -> {
            TypeName returnType = TypeName.get(en.getKey().getReturnType());
            String key = en.getKey().getSimpleName().toString();
            Object value = en.getValue().accept(new CustomAnnotationValueVisitor(key, msg -> print(msg)), null);

            print("key : %s , type : %s , value : %s ,value class : %s"
                    , key
                    , returnType
                    , value
                    , value.getClass());
            if (value instanceof List) {
                ((List) value).forEach(e -> {
                    print(String.format("value type %s, value object : %s", e.getClass().toString(), e.toString()));
                });
            } else if (value instanceof java.lang.Boolean) {
                print("i found Boolean value : %s ,@ key : %s.", value, key);
            } else if (value instanceof java.lang.String) {
                print("i found String value : %s ,@ key : %s.", value, key);
            } else if (value instanceof java.lang.Class) {
                print("i found Class value : %s ,@ key : %s.", value, key);
            } else if (value.getClass().getName().equalsIgnoreCase("com.sun.tools.javac.code.Type")) {
                print("i found Class value : %s ,@ key : %s.", value, key);
            } else if (value instanceof TypeMirror) {
                print("i found Class value : %s ,@ key : %s.", value, key);
            } else if (value instanceof VariableElement) {
                print("i found Enum value : %s ,@ key : %s.", value, key);
            } else {

            }

            if (returnType instanceof ArrayTypeName) {
                ArrayTypeName arrayTypeName = (ArrayTypeName) returnType;
                TypeName arrayComponentType = arrayTypeName.componentType;
                if (arrayComponentType.equals(TypeName.get(String.class))) {
                    print(" is String[] type by paranora , name : %s", key);
                } else if (arrayComponentType.toString().startsWith(Class.class.getName())) {
                    print(" is Class[] type by paranora , name : %s", key);
                } else {
                }
                print(String.format("componentType : %s", arrayTypeName.componentType));
            }

//                        Object defaultValue = en.getKey().getDefaultValue().getValue();
//                        if (null != defaultValue) {
//                            print(String.format("name : %s, defaultValue : %s", en.getKey().getSimpleName(), defaultValue.getClass()));
//                        }
        });
    }

    public boolean processA(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        print("entry.");
        processPresentAnnotation(annotations, roundEnvironment, PMapper.class, element -> {
            TypeMirror typeMirror = element.asType();
            processClassFields(element, variableElement -> {
                print("class %s field : %s ", typeMirror.toString(), variableElement.getSimpleName());
                PMapping annotation = variableElement.getAnnotation(PMapping.class);
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
                                , PMapping.class.getSimpleName()
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
            PMapper pMapper = element.getAnnotation(PMapper.class);
            String classFullName = element.asType().toString();
            String className = element.getSimpleName().toString();

            Element enclosingElement = element.getEnclosingElement();
            String enclosingQualifiedname;
            if (enclosingElement instanceof PackageElement) {
                enclosingQualifiedname = ((PackageElement) enclosingElement).getQualifiedName().toString();
            } else {
                enclosingQualifiedname = ((TypeElement) enclosingElement).getQualifiedName().toString();
            }
            print("mpMapper [name : %s] ", pMapper.name());
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
