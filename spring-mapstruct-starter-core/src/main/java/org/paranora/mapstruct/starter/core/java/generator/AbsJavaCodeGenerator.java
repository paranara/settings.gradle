package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.Definition;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public abstract class AbsJavaCodeGenerator<D extends Definition, T extends Object> implements JavaCodeGenerator<D,T> {


    protected CodeBlock createCode(TypeName typeName,Object value) {
        if (null == value) return null;
        CodeBlock codeBlock = null;
        if (typeName.isPrimitive()) {
            codeBlock = createPrimitiveCode(typeName,value);
        } else if (value instanceof List && typeName instanceof ArrayTypeName) {
            codeBlock = createArraryCode(typeName,value);
        } else if (value instanceof java.lang.String) {
            codeBlock = createStringCode(typeName,value);
        } else if (value instanceof TypeMirror) {
            codeBlock = createClassCode(typeName,value);
        } else if (value instanceof VariableElement) {
            codeBlock = createEnumCode(typeName,value);
        } else {

        }
        return codeBlock;
    }

    protected CodeBlock createPrimitiveCode(TypeName typeName,Object value) {
        return CodeBlock.builder().add("$L", value).build();
    }

    protected CodeBlock createStringCode(TypeName typeName,Object value) {
        return  CodeBlock.builder().add("$S", value).build();
    }

    protected CodeBlock createClassCode(TypeName typeName,Object value) {
        return CodeBlock.builder().add("$T.class", value).build();
    }

    protected CodeBlock createEnumCode(TypeName typeName,Object value) {
        return CodeBlock.builder().add("$T.$L", typeName, value).build();
    }

    protected CodeBlock createArraryCode(TypeName typeName,Object value) {
        List list = (List) value;
        if (list.size() < 1) return null;
        CodeBlock codeBlock = CodeBlock.builder().add("$L",
                        list.stream()
                                .map(v -> createCode(TypeName.get(v.getClass()),v))
                                .collect(CodeBlock.joining(",", "{", "}")))
                .build();
        return codeBlock;
    }
}
