package org.paranora.mapstruct.starter.core.java.generator.definition.extractor;

import org.paranora.mapstruct.starter.core.java.generator.definition.entity.AnnotationDefinition;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.AnnotationFieldDefinition;

import java.util.ArrayList;
import java.util.List;

public  abstract class AbsAnnotationDefinitionExtractor<S extends Object, AOT extends Object> implements AnnotationDefinitionExtractor<S> {

    public AnnotationDefinition extract(S source,AOT annotationObj) {
        AnnotationDefinition definition = new AnnotationDefinition();
        definition.setFields(extractFields(source,annotationObj));
        return definition;
    }

    protected abstract List<AnnotationFieldDefinition> extractFields(S source,AOT annotationObj);

    public abstract List<AOT> getAnnotations(S source);

    @Override
    public List<AnnotationDefinition> extract(S source) {
        List<AnnotationDefinition> definitions=new ArrayList<>();
        List<AOT> annotations=getAnnotations(source);
        annotations.forEach(a->{
            definitions.add(extract(source,a));
        });
        return definitions;
    }
}
