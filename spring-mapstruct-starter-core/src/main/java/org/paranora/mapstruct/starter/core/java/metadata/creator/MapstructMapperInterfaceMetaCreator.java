package org.paranora.mapstruct.starter.core.java.metadata.creator;

import org.paranora.mapstruct.starter.core.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.starter.core.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.starter.core.java.metadata.extractor.DefaultElementClassMetaExtractor;
import org.paranora.mapstruct.starter.core.java.metadata.extractor.ElementClassMetaExtractor;

import javax.lang.model.element.TypeElement;
import java.util.List;

public class MapstructMapperInterfaceMetaCreator implements InterfaceMetaCreator<TypeElement> {

    protected ElementClassMetaExtractor elementClassDefinitionExtractor;

    public MapstructMapperInterfaceMetaCreator() {
        init();
    }

    protected void init() {
        this.defaultMapstructMapperInterfaceCreator(new DefaultElementClassMetaExtractor());
    }

    protected void defaultMapstructMapperInterfaceCreator(ElementClassMetaExtractor elementClassDefinitionExtractor) {
        this.elementClassDefinitionExtractor = elementClassDefinitionExtractor;
    }

    @Override
    public InterfaceMeta extract(TypeElement source) {
        List<ClassMeta> sourceClass = elementClassDefinitionExtractor.extract(source);
        return null;
    }
}
