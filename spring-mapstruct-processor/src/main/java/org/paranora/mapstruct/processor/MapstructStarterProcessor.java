package org.paranora.mapstruct.processor;

import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.code.DefaultMapstructFileGenerator;
import org.paranora.mapstruct.java.code.MapstructFileGenerator;


import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.*;

@SupportedAnnotationTypes(PMapper.ClassFullName)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MapstructStarterProcessor extends AbsProcessor {

    protected MapstructFileGenerator mapstructFileGenerator;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.mapstructFileGenerator = new DefaultMapstructFileGenerator(this.filer);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        processPresentAnnotation(annotations, roundEnvironment, PMapper.class, element -> {
            mapstructFileGenerator.generate(element);
        });
        return false;
    }

    @Override
    protected String prefix() {
        return "PM.Process entry. ";
    }

}
