package org.paranora.mapstruct.java.generator.poet;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.paranora.mapstruct.java.generator.ClassJavaCodeGenerator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import java.util.List;


public class DefaultClassGenerator extends AbsJavapoetClassGenerator<ClassMeta, TypeSpec> implements ClassJavapoetGenerator {


    @Override
    protected List<FieldSpec> createFields(ClassMeta meta) {
        return null;
    }
}
