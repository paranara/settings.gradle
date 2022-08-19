package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.TypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnotationFieldDefinition {
    private String name;
    private TypeName type;
    private Object value;
}
