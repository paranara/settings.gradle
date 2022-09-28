package org.paranora.mapstruct.java.code.poet;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.java.metadata.entity.ValueMeta;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class DefaultValueJavapoetGenerator implements ValueJavapoetGenerator {

    protected CodeBlock createPrimitiveCode(ValueMeta meta) {
        return CodeBlock.builder().add("$L", meta.getValue()).build();
    }

    protected CodeBlock createStringCode(ValueMeta meta) {
        return CodeBlock.builder().add("$S", meta.getValue()).build();
    }

    protected CodeBlock createClassCode(ValueMeta meta) {
        if (meta.getValue() instanceof String) {
            return CodeBlock.builder().add("$L", meta.getValue()).build();
        }
        return CodeBlock.builder().add("$T.class", meta.getValue()).build();
    }

    protected CodeBlock createEnumCode(ValueMeta meta) {
        return CodeBlock.builder().add("$T.$L", meta.getTypeName(), meta.getValue()).build();
    }

    protected CodeBlock createArraryCode(ValueMeta meta) {
        List list = (List) meta.getValue();
        if (list.size() < 1) return null;
        CodeBlock codeBlock = CodeBlock.builder().add("$L",
                        list.stream()
                                .map(v -> create(ValueMeta.builder().typeName(TypeName.get(v.getClass())).value(v).build()))
                                .collect(CodeBlock.joining(",", "{", "}")))
                .build();
        return codeBlock;
    }


    @Override
    public CodeBlock create(ValueMeta meta) {
        if (null == meta || null == meta.getTypeName() || null == meta.getValue()) return null;
        TypeName typeName = meta.getTypeName();
        Object value = meta.getValue();
        CodeBlock codeBlock = null;
        if (meta.getTypeName().isPrimitive()) {
            codeBlock = createPrimitiveCode(meta);
        } else if (value instanceof List && typeName instanceof ArrayTypeName) {
            codeBlock = createArraryCode(meta);
        } else if (typeName.equals(TypeName.get(String.class)) && value instanceof java.lang.String) {
            codeBlock = createStringCode(meta);
        } else if (typeName.equals(TypeName.get(Class.class))|| value instanceof TypeMirror || value instanceof Class) {
            codeBlock = createClassCode(meta);
        } else if (value instanceof VariableElement || value instanceof Enum) {
            codeBlock = createEnumCode(meta);
        } else {

        }
        return codeBlock;
    }
}
