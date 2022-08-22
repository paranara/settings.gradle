package org.paranora.mapstruct.starter.core.java.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.lang.model.element.Modifier;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClassDefinition extends InterfaceDefinition {
    protected List<FieldDefinition> fields;
}
