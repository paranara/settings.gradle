package org.paranora.mapstruct.java.metadata.extractor;

import org.paranora.mapstruct.java.metadata.entity.ClassMeta;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;


public abstract class AbsElementTypeMetaExtractor extends AbsMetaExtractor<TypeElement,ClassMeta> implements ElementClassMetaExtractor {

    protected Elements elementUtils;
    protected Types typesUtils;


    protected String readPackageName(TypeElement source) {
        if (source instanceof TypeElement) {
            TypeElement typeElement = (TypeElement) source;
            String name = typeElement.getQualifiedName().toString();
            return name.substring(0, name.lastIndexOf('.'));
        }
        return null;
    }


    public void setElementUtils(Elements elementUtils) {
        this.elementUtils = elementUtils;
    }

    public void setTypesUtils(Types typesUtils) {
        this.typesUtils = typesUtils;
    }
}
