package org.paranora.mapstruct.starter.core.java.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Type;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ClassDefinition extends InterfaceDefinition {
    protected List<FieldDefinition> fields;
    protected Type superClass;
}
