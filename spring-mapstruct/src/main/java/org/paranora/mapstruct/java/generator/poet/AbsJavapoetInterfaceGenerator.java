package org.paranora.mapstruct.java.generator.poet;


import com.squareup.javapoet.*;
import org.paranora.mapstruct.java.generator.poet.*;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbsJavapoetInterfaceGenerator<D extends InterfaceMeta, T extends TypeSpec> extends AbsJavapoetTypeGenerator<D, T> {
    @Override
    protected TypeSpec.Builder createTypeSpec(InterfaceMeta meta) {
        return TypeSpec.interfaceBuilder(meta.getName());
    }

}
