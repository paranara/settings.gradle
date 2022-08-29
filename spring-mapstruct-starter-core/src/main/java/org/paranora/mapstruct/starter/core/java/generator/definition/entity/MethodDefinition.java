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
@EqualsAndHashCode(callSuper=false)
public class MethodDefinition extends MetaDefinition {
    protected TypeName returnType;

    @Builder.Default
    protected List<MethodParameterDefinition> parameters=new ArrayList<>();
}
