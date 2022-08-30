package org.paranora.mapstruct.starter.core.java.generator.definition.entity;

import com.squareup.javapoet.TypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MetaDefinition {
    protected String packageName;
    protected String name;
    protected TypeName typeName;
}
