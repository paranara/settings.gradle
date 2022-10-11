package org.paranora.mapstruct.java.code;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
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

import javax.lang.model.element.*;
import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;

public abstract class AbsMapstructFileGenerator implements MapstructFileElementGenerator {

    protected ElementClassMetaExtractor elementClassMetaExtractor;

    protected ClassMetaCreator classMetaCreator;

    protected InterfaceMetaCreator interfaceMetaCreator;

    protected InterfaceJavapoetGenerator interfaceJavapoetGenerator;

    protected ClassJavapoetGenerator classJavapoetGenerator;

    protected Filer filer;

    public AbsMapstructFileGenerator(Filer filer) {
        this.init();
        this.filer=filer;
    }

    protected void init() {
        this.elementClassMetaExtractor = new DefaultElementClassMetaExtractor();
        this.classMetaCreator = new MapstructDecoratorClassMetaCreatorFacader();
        this.interfaceMetaCreator = new MapstructMapperInterfaceMetaCreatorFacader();
        this.interfaceJavapoetGenerator = new DefaultInterfaceGenerator();
        this.classJavapoetGenerator = new DefaultClassGenerator();
    }

    @Override
    public void generate(Element source) {
        try {
            ClassMeta sourceClasz = elementClassMetaExtractor.extract((TypeElement) source);
            InterfaceMeta interfaceMeta = (InterfaceMeta) interfaceMetaCreator.create(sourceClasz, null, null);
            TypeSpec interfaceSpec = interfaceJavapoetGenerator.create(interfaceMeta);
            JavaFile.builder(interfaceMeta.getPackageName(), interfaceSpec)
                    .build()
                    .writeTo(filer);

            ClassMeta classMeta = (ClassMeta) classMetaCreator.create(interfaceMeta, null, null);
            TypeSpec classSpec = classJavapoetGenerator.create(classMeta);
            JavaFile.builder(classMeta.getPackageName(), classSpec)
                    .build()
                    .writeTo(filer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
