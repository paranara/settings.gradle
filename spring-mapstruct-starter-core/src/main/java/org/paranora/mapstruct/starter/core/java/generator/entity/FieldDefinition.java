package org.paranora.mapstruct.starter.core.java.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.lang.model.element.Modifier;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class FieldDefinition  extends Definition{
    protected Modifier accession;
    protected Object value;
}
