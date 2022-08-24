package org.paranora.mapstruct.starter.core.java.generator;

import org.paranora.mapstruct.starter.core.java.generator.entity.AnnotationDefinition;

public interface AnnotationDefinitionCreator<T> {
    AnnotationDefinition create(T arg);
}
