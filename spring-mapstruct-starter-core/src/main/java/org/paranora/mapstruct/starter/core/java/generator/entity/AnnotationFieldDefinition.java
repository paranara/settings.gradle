package org.paranora.mapstruct.starter.core.java.generator.entity;

import com.squareup.javapoet.CodeBlock;
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
public class AnnotationFieldDefinition {
    protected String name;
    protected TypeName typeName;
    protected Object value;
    protected CodeBlock codeBlock;
}
