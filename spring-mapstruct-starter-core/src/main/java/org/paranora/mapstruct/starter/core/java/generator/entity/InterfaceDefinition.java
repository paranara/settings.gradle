package org.paranora.mapstruct.starter.core.java.generator.entity;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InterfaceDefinition {
    protected String packageName;
    protected String name;
    protected Modifier accession;
    protected CodeBlock code;

    @Builder.Default
    protected List<AnnotationDefinition> annotations=new ArrayList<>();

    @Builder.Default
    protected List<MethodDefinition> methods=new ArrayList<>();

    @Builder.Default
    protected List<TypeName> superInterfaces=new ArrayList<>();
}
