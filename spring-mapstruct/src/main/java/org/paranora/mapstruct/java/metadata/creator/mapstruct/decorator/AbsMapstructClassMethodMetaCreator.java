package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.CodeBlock;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructMethodMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;


public abstract class AbsMapstructClassMethodMetaCreator<S extends TypeMeta,TP extends TypeMeta> extends AbsMapstructMethodMetaCreator<S,TP> {


    protected abstract CodeBlock createBody(S source, TP parent, Class<?> clasz);
}
