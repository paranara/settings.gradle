package org.paranora.mapstruct.processor;

import com.squareup.javapoet.*;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.code.generator.poet.ClassJavapoetGenerator;
import org.paranora.mapstruct.java.code.generator.poet.DefaultClassGenerator;
import org.paranora.mapstruct.java.code.generator.poet.DefaultInterfaceGenerator;
import org.paranora.mapstruct.java.code.generator.poet.InterfaceJavapoetGenerator;
import org.paranora.mapstruct.java.metadata.creator.ClassMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.creator.facader.MapstructDecoratorClassMetaCreatorFacader;
import org.paranora.mapstruct.java.metadata.creator.facader.MapstructMapperInterfaceMetaCreatorFacader;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.extractor.DefaultElementClassMetaExtractor;
import org.paranora.mapstruct.java.metadata.extractor.ElementClassMetaExtractor;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@SupportedAnnotationTypes(PMapper.ClassFullName)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MapstructStarterProcessor extends AbsProcessor {

    protected InterfaceMetaCreator interfaceMetaCreator = new MapstructMapperInterfaceMetaCreatorFacader();

    protected ClassMetaCreator classMetaCreator = new MapstructDecoratorClassMetaCreatorFacader();

    protected InterfaceJavapoetGenerator interfaceJavapoetGenerator = new DefaultInterfaceGenerator();

    protected ClassJavapoetGenerator classJavapoetGenerator = new DefaultClassGenerator();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        processPresentAnnotation(annotations, roundEnvironment, PMapper.class, element -> {
            ElementClassMetaExtractor elementClassDefinitionExtractor = new DefaultElementClassMetaExtractor();
            ClassMeta clz = elementClassDefinitionExtractor.extract((TypeElement) element);

            try {
                InterfaceMeta interfaceMeta = (InterfaceMeta) interfaceMetaCreator.create(clz, null, null);
                TypeSpec typeSpec = interfaceJavapoetGenerator.create(interfaceMeta);
                JavaFile.builder(interfaceMeta.getPackageName(), typeSpec)
                        .build()
                        .writeTo(filer);

                ClassMeta classMeta = (ClassMeta) classMetaCreator.create(interfaceMeta, null, null);
                TypeSpec classSpec = classJavapoetGenerator.create(classMeta);
                JavaFile.builder(classMeta.getPackageName(), classSpec)
                        .build()
                        .writeTo(filer);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


//                interfaceMeta.getAnnotations().forEach((at) -> {
//                    print("package : %s , class : %s , annotation  : %s  , class : %s"
//                            , clz.getPackageName()
//                            , clz.getName()
//                            , at.getName()
//                            , at.getTypeName());
//                    at.getFields().forEach((atfk, atf) -> {
//                        print("package : %s , class : %s , annotation key : %s , type : %s , value : %s ,value class : %s"
//                                , clz.getPackageName()
//                                , clz.getName()
//                                , atf.getName()
//                                , atf.getTypeName()
//                                , atf.getValue()
//                                , atf.getValue().getClass());
//                    });
//                    print("\r\n\r\n");
//                });
//
//                interfaceMeta.getMethods()
//                        .stream()
//                        .forEach(m -> {
//                            print("package : %s , class : %s , method  : %s "
//                                    , clz.getPackageName()
//                                    , clz.getName()
//                                    , m.getName());
//                            m.getAnnotations().forEach((at) -> {
//                                print("package : %s , class : %s , method : %s , annotation  : %s ."
//                                        , clz.getPackageName()
//                                        , clz.getName()
//                                        , m.getName()
//                                        , at.getName());
//                                at.getFields().forEach((atfk, atf) -> {
//                                    print("package : %s , class : %s , method : %s  , annotation key : %s , type : %s , value : %s ,value class : %s"
//                                            , clz.getPackageName()
//                                            , clz.getName()
//                                            , m.getName()
//                                            , atf.getName()
//                                            , atf.getTypeName()
//                                            , atf.getValue()
//                                            , atf.getValue().getClass());
//                                });
//                                print("\r\n\r\n");
//                            });
//                        });

//                clz.getFields().forEach((fk, f) -> {
//                    print("package : %s , class %s , field : %s ", clz.getPackageName(), clz.getName(), f.getName());
//                    f.getAnnotations().forEach((at) -> {
//                        at.getFields().forEach((atfk, atf) -> {
//                            print("package : %s , class : %s , field : %s , annotation key : %s , type : %s , value : %s ,value class : %s"
//                                    , at.getPackageName()
//                                    , at.getName()
//                                    , f.getName()
//                                    , atf.getName()
//                                    , atf.getTypeName()
//                                    , atf.getValue()
//                                    , atf.getValue().getClass());
//                        });
//                    });
//                });
        });
        return false;
    }

    @Override
    protected String prefix() {
        return "PM.Process entry. ";
    }

}
