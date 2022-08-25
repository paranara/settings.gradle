package org.paranora.mapstruct.starter.core.java.generator.entity;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodParameterDefinition {
    protected String name;
    protected TypeName typeName;

    @Builder.Default
    protected List<AnnotationDefinition> annotations=new ArrayList<>();

    protected CodeBlock code;

}
