package org.paranora.mapstruct.java.code.generator.poet;


import org.paranora.mapstruct.java.code.generator.JavaCodeGenerator;
import org.paranora.mapstruct.java.metadata.entity.Meta;


public abstract class AbsJavapoetGenerator<D extends Meta, T extends Object> implements JavaCodeGenerator<D, T> {

    protected ValueJavapoetGenerator valueJavapoetGenerator;

    public AbsJavapoetGenerator() {
        init();
    }

    protected void init(){
        this.valueJavapoetGenerator =defaultCodeJavapoetGenerator();
    }

    protected ValueJavapoetGenerator defaultCodeJavapoetGenerator() {
        return new DefaultValueJavapoetGenerator();
    }

    protected void defaultCodeJavapoetGenerator(ValueJavapoetGenerator valueJavapoetGenerator){
        this.valueJavapoetGenerator = valueJavapoetGenerator;
    }

}
