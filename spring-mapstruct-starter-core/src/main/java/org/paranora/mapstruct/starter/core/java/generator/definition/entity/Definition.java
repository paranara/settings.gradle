package org.paranora.mapstruct.starter.core.java.generator.definition.entity;

import com.squareup.javapoet.TypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public abstract class Definition {
    protected String packageName;
    protected String name;
    protected TypeName typeName;

    @Builder.Default
    protected List<AnnotationDefinition> annotations=new ArrayList<>();
}
