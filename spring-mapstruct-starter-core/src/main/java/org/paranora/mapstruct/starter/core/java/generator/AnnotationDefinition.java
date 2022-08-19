package org.paranora.mapstruct.starter.core.java.generator;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnnotationDefinition {
        private List<AnnotationFieldDefinition> fields;
}
