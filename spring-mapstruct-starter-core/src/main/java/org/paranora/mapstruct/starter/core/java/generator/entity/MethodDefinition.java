package org.paranora.mapstruct.starter.core.java.generator.entity;

import com.squareup.javapoet.CodeBlock;
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
public class MethodDefinition {
    protected String name;
    protected Modifier accession;
    protected Class<?> returnType;
    protected List<MethodParameterDefinition> parameters;
    protected CodeBlock code;
    protected AnnotationDefinition annotations;
}
