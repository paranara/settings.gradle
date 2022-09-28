package org.paranora.mapstruct.java.code.generator.poet;


import com.squareup.javapoet.*;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

public abstract class AbsJavapoetInterfaceGenerator<D extends InterfaceMeta, T extends TypeSpec> extends AbsJavapoetTypeGenerator<D, T> {
    @Override
    protected TypeSpec.Builder createTypeSpec(InterfaceMeta meta) {
        return TypeSpec.interfaceBuilder(meta.getName());
    }

}
