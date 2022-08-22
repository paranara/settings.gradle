package org.paranora.mapstruct.starter.core.java.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodParameterDefinition {
    protected String name;
    protected Class<?> type;
    protected List<AnnotationDefinition> annotations;
}
