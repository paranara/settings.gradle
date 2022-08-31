package org.paranora.mapstruct.starter.core.java.generator.poet;

import com.squareup.javapoet.ParameterSpec;
import org.paranora.mapstruct.starter.core.java.metadata.entity.MethodParameterMeta;

import javax.lang.model.element.Modifier;


public class DefaultParameterGenerator extends AbsJavapoetGenerator<MethodParameterMeta, ParameterSpec> implements ParameterJavapoetGenerator {

    @Override
    public ParameterSpec create(MethodParameterMeta definition) {
        ParameterSpec parameterSpec = ParameterSpec.builder(definition.getTypeName(), definition.getName(), definition.getAccessLevels().toArray(new Modifier[]{})).build();
        return parameterSpec;
    }
}
