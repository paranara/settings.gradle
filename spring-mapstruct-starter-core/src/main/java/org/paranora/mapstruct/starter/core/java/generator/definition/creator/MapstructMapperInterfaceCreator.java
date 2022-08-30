package org.paranora.mapstruct.starter.core.java.generator.definition.creator;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.InterfaceDefinition;
import org.paranora.mapstruct.starter.core.java.generator.definition.extractor.DefaultElementClassDefinitionExtractor;
import org.paranora.mapstruct.starter.core.java.generator.definition.extractor.ElementClassDefinitionExtractor;

import javax.lang.model.element.Element;
import java.util.List;

public class MapstructMapperInterfaceCreator implements DefinitionCreator<Element, InterfaceDefinition> {

    protected ElementClassDefinitionExtractor elementClassDefinitionExtractor;

    public MapstructMapperInterfaceCreator(){
        init();
    }

    protected void init(){
        this.defaultMapstructMapperInterfaceCreator(new DefaultElementClassDefinitionExtractor());
    }

    protected void defaultMapstructMapperInterfaceCreator(ElementClassDefinitionExtractor elementClassDefinitionExtractor){
        this.elementClassDefinitionExtractor=elementClassDefinitionExtractor;
    }

    @Override
    public List<InterfaceDefinition> extract(Element source) {
        return null;
    }
}
