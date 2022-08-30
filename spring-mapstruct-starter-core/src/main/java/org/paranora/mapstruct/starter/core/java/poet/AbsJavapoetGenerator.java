package org.paranora.mapstruct.starter.core.java.poet;


import org.paranora.mapstruct.starter.core.java.generator.JavaCodeGenerator;
import org.paranora.mapstruct.starter.core.java.generator.definition.entity.MetaDefinition;


public abstract class AbsJavapoetGenerator<D extends MetaDefinition, T extends Object> implements JavaCodeGenerator<D, T> {

    protected CodeJavapoetGenerator codeJavapoetGenerator;

    public AbsJavapoetGenerator() {
        init();
    }

    protected void init(){
        this.codeJavapoetGenerator=defaultCodeJavapoetGenerator();
    }

    protected CodeJavapoetGenerator defaultCodeJavapoetGenerator() {
        return new DefaultCodeJavapoetGenerator();
    }

    protected void defaultCodeJavapoetGenerator(CodeJavapoetGenerator codeJavapoetGenerator){
        this.codeJavapoetGenerator=codeJavapoetGenerator;
    }

}
