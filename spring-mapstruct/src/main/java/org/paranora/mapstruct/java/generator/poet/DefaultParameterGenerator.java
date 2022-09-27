package org.paranora.mapstruct.java.generator.poet;

import com.squareup.javapoet.ParameterSpec;
import org.paranora.mapstruct.java.metadata.entity.ParameterMeta;

import javax.lang.model.element.Modifier;


public class DefaultParameterGenerator extends AbsJavapoetGenerator<ParameterMeta, ParameterSpec> implements ParameterJavapoetGenerator {

    @Override
    public ParameterSpec create(ParameterMeta meta) {
        ParameterSpec parameterSpec = ParameterSpec.builder(meta.getTypeName(), meta.getName(), meta.getAccessLevels().toArray(new Modifier[]{})).build();
        return parameterSpec;
    }
}
