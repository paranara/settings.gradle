package org.paranora.mapstruct.starter.core.java.generator.entity;

import com.squareup.javapoet.CodeBlock;
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
public class AnnotationDefinition {

    protected String packageName;
    protected String simpleName;
    protected CodeBlock code;

    @Builder.Default
    protected List<AnnotationFieldDefinition> fields=new ArrayList<>();
}
