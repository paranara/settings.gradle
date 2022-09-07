package org.paranora.mapstruct.starter.core.java.metadata.creator.factory;


import org.paranora.mapstruct.starter.core.java.metadata.creator.AnnotationMetaCreator;
import org.paranora.mapstruct.starter.core.java.metadata.creator.MethodMetaCreator;
import org.paranora.mapstruct.starter.core.java.metadata.creator.ParameterMetaCreator;

public interface MetaCreatorFactory {
    AnnotationMetaCreator annotationCreator();
    MethodMetaCreator methodCreator();
    ParameterMetaCreator parameterCreator();

}
