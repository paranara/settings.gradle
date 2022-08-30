package org.paranora.mapstruct.starter.core.java.generator.definition.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValueDefinition extends MetaDefinition {
    protected Object value;

}
