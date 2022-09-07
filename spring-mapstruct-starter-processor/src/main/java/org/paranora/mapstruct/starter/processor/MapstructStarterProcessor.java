package org.paranora.mapstruct.starter.processor;

import org.paranora.mapstruct.starter.core.annotations.PMapper;
import org.paranora.mapstruct.starter.core.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.starter.core.java.metadata.extractor.DefaultElementClassMetaExtractor;
import org.paranora.mapstruct.starter.core.java.metadata.extractor.ElementClassMetaExtractor;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.*;


@SupportedAnnotationTypes(MapstructStarterProcessor.PMapperAnnotationName)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MapstructStarterProcessor extends AbsProcessor {

    public static final String PMapperAnnotationName = "org.paranora.mapstruct.starter.core.annotations.PMapper";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        print("entry.");
        processPresentAnnotation(annotations, roundEnvironment, PMapper.class, element -> {
            ElementClassMetaExtractor elementClassDefinitionExtractor = new DefaultElementClassMetaExtractor();
            List<ClassMeta> definitions = elementClassDefinitionExtractor.extract((TypeElement) element);
            definitions.forEach(clz -> {
                clz.getAnnotations().forEach(at -> {
                    at.getFields().stream().forEach(atf -> {
                        print("package : %s , class : %s , annotation key : %s , type : %s , value : %s ,value class : %s"
                                , clz.getPackageName()
                                , clz.getName()
                                , atf.getName()
                                , atf.getTypeName()
                                , atf.getValue()
                                , atf.getValue().getClass());
                    });
                });
                clz.getFields().forEach(f -> {
                    print("package : %s , class %s , field : %s ", clz.getPackageName(), clz.getName(), f.getName());
                    f.getAnnotations().forEach(at -> {
                        at.getFields().forEach(atf->{
                            print("package : %s , class : %s , field : %s , annotation key : %s , type : %s , value : %s ,value class : %s"
                                    , at.getPackageName()
                                    , at.getName()
                                    , f.getName()
                                    , atf.getName()
                                    , atf.getTypeName()
                                    , atf.getValue()
                                    , atf.getValue().getClass());
                        });
                    });
                });
            });
        });
        return false;
    }

    @Override
    protected String prefix() {
        return "PM.Process entry. ";
    }

}
