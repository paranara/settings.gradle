package org.paranora.mapstruct.starter.core.java.generator.definition.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AmpleValueDefinition extends AmpleDefinition {

    protected Object value;
}
