package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import org.paranora.mapstruct.java.metadata.creator.ParameterMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructMultipleMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.ParameterMeta;

public abstract class AbsMapstructClassMethodParameterMetaCreator
        extends AbsMapstructMultipleMetaCreator<InterfaceMeta, ParameterMeta, ClassMeta>
        implements ParameterMetaCreator<InterfaceMeta, ClassMeta> {


}