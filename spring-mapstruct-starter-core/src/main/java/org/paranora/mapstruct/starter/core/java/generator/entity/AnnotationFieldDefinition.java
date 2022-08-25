package org.paranora.mapstruct.starter.core.java.generator.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AnnotationFieldDefinition extends Definition {
    protected Object value;
}
