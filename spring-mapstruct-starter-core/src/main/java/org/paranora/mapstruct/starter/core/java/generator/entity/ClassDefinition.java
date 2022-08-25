package org.paranora.mapstruct.starter.core.java.generator.entity;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ClassDefinition extends InterfaceDefinition {

    @Builder.Default
    protected List<FieldDefinition> fields=new ArrayList<>();
    protected TypeName superClass;
}
