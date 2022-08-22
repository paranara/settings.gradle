package org.paranora.mapstruct.starter.core.java.generator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Modifier;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldDefinition {
    protected Class<?> type;
    protected String name;
    protected Modifier accession;
    protected Object initializationValue;
    protected List<AnnotationDefinition> annotations;
}
