package org.paranora.mapstruct.starter.core.java.generator;

import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;

public interface AnnotationDefinitionCreator<S extends Object> {
   AnnotationDefinition create(S source, String annotaionClassName);
   AnnotationDefinition create(S source, Class annotaionClass);
}
