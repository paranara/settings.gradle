package org.paranora.mapstruct.starter.core.java.generator.definition.entity;

import com.squareup.javapoet.TypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class InterfaceDefinition extends Definition {

    protected Modifier accession;

    @Builder.Default
    protected List<MethodDefinition> methods=new ArrayList<>();

    @Builder.Default
    protected List<TypeName> superInterfaces=new ArrayList<>();
}
