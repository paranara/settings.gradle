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
public class InterfaceDefinition extends AmpleDefinition {

    @Builder.Default
    protected List<MethodDefinition> methods=new ArrayList<>();

    @Builder.Default
    protected List<InterfaceDefinition> superInterfaces=new ArrayList<>();
}
