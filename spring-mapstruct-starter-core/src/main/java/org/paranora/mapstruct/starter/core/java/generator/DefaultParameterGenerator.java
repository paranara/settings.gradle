package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.ParameterSpec;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MethodParameterDefinition;

import javax.lang.model.element.Modifier;


public class DefaultParameterGenerator extends AbsJavapoetGenerator<MethodParameterDefinition, ParameterSpec> implements ParameterJavapoetGenerator {

    @Override
    public ParameterSpec create(MethodParameterDefinition definition) {
        ParameterSpec parameterSpec = ParameterSpec.builder(definition.getTypeName(), definition.getName(), definition.getAccessLevels().toArray(new Modifier[]{})).build();
        return parameterSpec;
    }
}
