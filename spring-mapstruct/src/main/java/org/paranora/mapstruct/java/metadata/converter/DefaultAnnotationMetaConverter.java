package org.paranora.mapstruct.java.metadata.converter;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import lombok.Synchronized;
import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;

import java.lang.reflect.Method;
import java.util.*;

public class DefaultAnnotationMetaConverter implements AnnotationMetaConverter {

    protected Map<String, List<Method>> methodMap = new HashMap<>();

    @Override
    public AnnotationMeta convert(AnnotationMeta source, Class targetClass) {
        if (null == source) return null;
        AnnotationMeta meta = AnnotationMeta.builder().build();
        meta.setPackageName(targetClass.getPackage().getName());
        meta.setName(targetClass.getSimpleName());
        getClassMethods(targetClass)
                .stream()
                .forEach(m -> {
                    Optional<AnnotationFieldMeta> opt = source.getFields()
                            .values()
                            .stream()
                            .filter(a -> {
                                boolean check = a.getName().equalsIgnoreCase(m.getName());
                                TypeName t0 = TypeName.get(m.getReturnType());
                                TypeName t1 = a.getTypeName();
                                if (check && t0 instanceof ArrayTypeName && t1 instanceof ArrayTypeName) {
                                    t0 = ((ArrayTypeName) t0).componentType;
                                    t1 = ((ArrayTypeName) t1).componentType;
                                    if (!t0.equals(t1) && (t1 instanceof ParameterizedTypeName || t0 instanceof ParameterizedTypeName)) {
                                        if (t0 instanceof ParameterizedTypeName) {
                                            t0 = ((ParameterizedTypeName) t0).rawType;
                                        }
                                        if (t1 instanceof ParameterizedTypeName) {
                                            t1 = ((ParameterizedTypeName) t1).rawType;
                                        }
                                    }
                                }
                                return check & (t0.equals(t1));
                            })
                            .findFirst();

                    if (opt.isPresent()) {
                        meta.getFields().put(opt.get().getName()
                                , AnnotationFieldMeta.builder()
                                        .name(opt.get().getName())
                                        .typeName(opt.get().getTypeName())
                                        .value(opt.get().getValue())
                                        .annotations(opt.get().getAnnotations())
                                        .build());
                    }
                });
        if (meta.getFields().size() < 1) {
            return null;
        }
        return meta;
    }

    @Synchronized
    protected List<Method> getClassMethods(Class targetClass) {
        String name = targetClass.getName();
        if (!methodMap.containsKey(name)) {
            methodMap.put(name, Arrays.asList(targetClass.getDeclaredMethods()));
        }
        return methodMap.get(name);
    }
}
