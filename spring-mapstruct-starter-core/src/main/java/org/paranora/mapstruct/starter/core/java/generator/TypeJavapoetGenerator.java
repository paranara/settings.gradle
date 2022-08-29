package org.paranora.mapstruct.starter.core.java.generator;

import com.squareup.javapoet.TypeSpec;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;

public interface TypeJavapoetGenerator<D extends MetaDefinition> extends JavaCodeGenerator<D, TypeSpec> {


}
