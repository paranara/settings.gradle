package org.paranora.mapstruct.starter.core.java.generator.definition.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ClassDefinition extends InterfaceDefinition {

    @Builder.Default
    protected List<FieldDefinition> fields=new ArrayList<>();
    protected ClassDefinition superClass;
}
