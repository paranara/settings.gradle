package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import org.paranora.mapstruct.java.metadata.creator.MetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.Meta;
import org.paranora.mapstruct.java.metadata.entity.TypeMeta;

import javax.lang.model.util.Elements;

public abstract class AbsMapstructMetaCreator<S extends TypeMeta,E extends Meta,TP extends TypeMeta> implements MetaCreator<S, E, TP> {

    protected Elements elementUtils;

    public void setElementUtils(Elements elementUtils) {
        this.elementUtils = elementUtils;
    }
}