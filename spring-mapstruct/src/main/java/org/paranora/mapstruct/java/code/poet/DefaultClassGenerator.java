package org.paranora.mapstruct.java.code.poet;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import java.util.List;

public class DefaultClassGenerator extends AbsJavapoetClassGenerator<ClassMeta, TypeSpec> implements ClassJavapoetGenerator {

    @Override
    protected List<FieldSpec> createFields(ClassMeta meta) {
        return null;
    }
}
