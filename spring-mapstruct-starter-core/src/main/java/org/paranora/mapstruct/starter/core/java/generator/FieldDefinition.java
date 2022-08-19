package org.paranora.mapstruct.starter.core.java.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Modifier;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldDefinition {
    private Class<?> type;
    private String name;
    private Modifier accession;
    private Object initializationValue;
}
