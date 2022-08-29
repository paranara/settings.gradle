package org.paranora.mapstruct.starter.core.java.generator.definition.entity;

import com.squareup.javapoet.TypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class MetaDefinition {
    protected String packageName;
    protected String name;
    protected TypeName typeName;

    @Builder.Default
    protected List<Modifier> accessLevels = new ArrayList<>();

    @Builder.Default
    protected List<TypeName> genericTypes = new ArrayList<>();
    @Builder.Default
    protected List<AnnotationDefinition> annotations = new ArrayList<>();

    @Builder.Default
    protected List<Class<?>> annotationClazs = new ArrayList<>();
}
