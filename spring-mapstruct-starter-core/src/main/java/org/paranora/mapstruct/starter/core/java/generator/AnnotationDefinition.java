package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.TypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnotationDefinition {
    protected String name;
    protected TypeName typeName;
    protected Type type;
    protected Object value;
}
