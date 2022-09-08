package org.paranora.mapstruct.java.generator.poet;

import com.squareup.javapoet.ParameterSpec;
import org.paranora.mapstruct.java.metadata.entity.ParameterMeta;

import javax.lang.model.element.Modifier;


public class DefaultParameterGenerator extends AbsJavapoetGenerator<ParameterMeta, ParameterSpec> implements ParameterJavapoetGenerator {

    @Override
    public ParameterSpec create(ParameterMeta definition) {
        ParameterSpec parameterSpec = ParameterSpec.builder(definition.getTypeName(), definition.getName(), definition.getAccessLevels().toArray(new Modifier[]{})).build();
        return parameterSpec;
    }
}
