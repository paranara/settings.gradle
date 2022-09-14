package org.paranora.mapstruct.java.metadata.converter;

import lombok.Synchronized;
import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;

import java.lang.reflect.Method;
import java.util.*;

public class DefaultAnnotationMetaConverter extends AbsMetaConverter<AnnotationMeta, AnnotationMeta> implements AnnotationMetaConverter<AnnotationMeta> {

    protected Map<String, List<Method>> methodMap = new HashMap<>();

    @Override
    public List<AnnotationMeta> convert(AnnotationMeta source, Class targetClass) {
        AnnotationMeta result = new AnnotationMeta();
        result.setPackageName(targetClass.getPackage().getName());
        result.setName(targetClass.getSimpleName());
        getClassMethods(targetClass)
                .stream()
                .forEach(m -> {
                    Optional<AnnotationFieldMeta> opt = source.getFields()
                            .stream()
                            .filter(a -> a.getName().equalsIgnoreCase(m.getName()))
                            .findFirst();

                    if (opt.isPresent()) {
                        result.getFields().add(AnnotationFieldMeta.builder()
                                .name(opt.get().getName())
                                .typeName(opt.get().getTypeName())
                                .value(opt.get().getValue())
                                .annotations(new ArrayList<>(opt.get().getAnnotations()))
                                .build());
                    }
                });
        return Arrays.asList(result);
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
