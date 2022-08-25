package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationFieldDefinition;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class AnnotationJavaCodeCreator implements JavaCodeCreator<AnnotationDefinition, AnnotationSpec> {

    @Override
    public AnnotationSpec create(AnnotationDefinition definition) {
        if (null == definition) return null;
        AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassName.get(definition.getPackageName(), definition.getName()));
        if (null != definition.getFields() && definition.getFields().size() > 0) {
            definition.getFields().stream().forEach(f->{
                builder.addMember(f.getName(),createFieldCode(f));
            });
        }
        return builder.build();
    }

    protected CodeBlock createFieldCode(AnnotationFieldDefinition fieldDefinition) {
        if (null == fieldDefinition) return null;
        CodeBlock codeBlock = null;
        if (fieldDefinition.getTypeName().isPrimitive()) {
            codeBlock = createPrimitiveFieldCode(fieldDefinition);
        } else if (fieldDefinition.getValue() instanceof List && fieldDefinition.getTypeName() instanceof ArrayTypeName) {
            codeBlock = createArraryFieldCode(fieldDefinition);
        } else if (fieldDefinition.getValue() instanceof java.lang.String) {
            codeBlock = createStringFieldCode(fieldDefinition);
        } else if (fieldDefinition.getValue() instanceof TypeMirror) {
            codeBlock = createClassFieldCode(fieldDefinition);
        } else if (fieldDefinition.getValue() instanceof VariableElement) {
            codeBlock = createEnumFieldCode(fieldDefinition);
        } else {

        }
        return codeBlock;
    }

    protected CodeBlock createPrimitiveFieldCode(AnnotationFieldDefinition fieldDefinition) {
        return CodeBlock.builder().add("$L", fieldDefinition.getValue()).build();
    }

    protected CodeBlock createStringFieldCode(AnnotationFieldDefinition fieldDefinition) {
        return  CodeBlock.builder().add("$S", fieldDefinition.getValue()).build();
    }

    protected CodeBlock createClassFieldCode(AnnotationFieldDefinition fieldDefinition) {
        return CodeBlock.builder().add("$T.class", fieldDefinition.getValue()).build();
    }

    protected CodeBlock createEnumFieldCode(AnnotationFieldDefinition fieldDefinition) {
        return CodeBlock.builder().add("$T.$L", fieldDefinition.getTypeName(), fieldDefinition.getValue()).build();
    }

    protected CodeBlock createArraryFieldCode(AnnotationFieldDefinition fieldDefinition) {
        List list = (List) fieldDefinition.getValue();
        if (list.size() < 1) return null;
        CodeBlock codeBlock = CodeBlock.builder().add("$L",
                        list.stream()
                                .map(v -> createFieldCode(fieldDefinition))
                                .collect(CodeBlock.joining(",", "{", "}")))
                .build();
        return codeBlock;
    }
}
