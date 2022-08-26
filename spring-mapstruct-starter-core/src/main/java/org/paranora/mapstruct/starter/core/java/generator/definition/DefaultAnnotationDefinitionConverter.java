package org.paranora.mapstruct.starter.core.java.generator.definition;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.AnnotationFieldDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class DefaultAnnotationDefinitionConverter extends AbsDefinitionConverter<AnnotationDefinition, AnnotationDefinition> implements AnnotationDefinitionConverter {

    @Override
    public AnnotationDefinition convert(AnnotationDefinition source, Class targetClass) {
        AnnotationDefinition result = new AnnotationDefinition();
        result.setPackageName(targetClass.getPackage().getName());
        result.setName(targetClass.getSimpleName());
        Arrays.stream(targetClass.getDeclaredMethods()).forEach(m -> {
            Optional<AnnotationFieldDefinition> opt = source.getFields().stream().filter(a -> a.getName().equalsIgnoreCase(m.getName())).findFirst();
            if (opt.isPresent()) {
                result.getFields().add(AnnotationFieldDefinition.builder()
                        .name(opt.get().getName())
                        .typeName(opt.get().getTypeName())
                        .value(opt.get().getValue())
                        .annotations(new ArrayList<>(opt.get().getAnnotations()))
                        .build());
            }
        });
        return result;
    }
}
