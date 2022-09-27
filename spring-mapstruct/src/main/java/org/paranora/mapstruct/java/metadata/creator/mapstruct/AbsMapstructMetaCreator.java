package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import org.paranora.mapstruct.java.metadata.creator.MetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;

import javax.lang.model.util.Elements;

public abstract class AbsMapstructMetaCreator<E extends Meta> implements MetaCreator<ClassMeta, E, InterfaceMeta> {

    protected Elements elementUtils;

    public void setElementUtils(Elements elementUtils) {
        this.elementUtils = elementUtils;
    }
}