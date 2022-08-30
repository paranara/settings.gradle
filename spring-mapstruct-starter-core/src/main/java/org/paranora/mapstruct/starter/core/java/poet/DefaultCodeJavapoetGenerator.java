package org.paranora.mapstruct.starter.core.java.poet;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.ValueDefinition;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class DefaultCodeJavapoetGenerator implements CodeJavapoetGenerator {

    protected CodeBlock createPrimitiveCode(ValueDefinition definition) {
        return CodeBlock.builder().add("$L", definition.getValue()).build();
    }

    protected CodeBlock createStringCode(ValueDefinition definition) {
        return CodeBlock.builder().add("$S", definition.getValue()).build();
    }

    protected CodeBlock createClassCode(ValueDefinition definition) {
        return CodeBlock.builder().add("$T.class", definition.getValue()).build();
    }

    protected CodeBlock createEnumCode(ValueDefinition definition) {
        return CodeBlock.builder().add("$T.$L", definition.getTypeName(), definition.getValue()).build();
    }

    protected CodeBlock createArraryCode(ValueDefinition definition) {
        List list = (List) definition.getValue();
        if (list.size() < 1) return null;
        CodeBlock codeBlock = CodeBlock.builder().add("$L",
                        list.stream()
                                .map(v -> create(ValueDefinition.builder().typeName(TypeName.get(v.getClass())).value(v).build()))
                                .collect(CodeBlock.joining(",", "{", "}")))
                .build();
        return codeBlock;
    }


    @Override
    public CodeBlock create(ValueDefinition definition) {
        if (null == definition || null == definition.getTypeName() || null == definition.getValue()) return null;
        TypeName typeName = definition.getTypeName();
        Object value = definition.getValue();
        CodeBlock codeBlock = null;
        if (definition.getTypeName().isPrimitive()) {
            codeBlock = createPrimitiveCode(definition);
        } else if (value instanceof List && typeName instanceof ArrayTypeName) {
            codeBlock = createArraryCode(definition);
        } else if (value instanceof java.lang.String) {
            codeBlock = createStringCode(definition);
        } else if (value instanceof TypeMirror || value instanceof Class) {
            codeBlock = createClassCode(definition);
        } else if (value instanceof VariableElement || value instanceof Enum) {
            codeBlock = createEnumCode(definition);
        } else {

        }
        return codeBlock;
    }
}