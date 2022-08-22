package org.paranora.mapstruct.starter.core.java.generator.entity;

import com.squareup.javapoet.TypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.lang.model.element.Modifier;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InterfaceDefinition {
    protected String name;
    protected Modifier accession;
    protected List<AnnotationDefinition> annotations;
    protected List<MethodDefinition> methods;
    protected List<TypeName> superInterfaces;
}
