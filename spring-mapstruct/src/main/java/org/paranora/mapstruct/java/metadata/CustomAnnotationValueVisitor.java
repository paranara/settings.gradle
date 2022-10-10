package org.paranora.mapstruct.java.metadata;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CustomAnnotationValueVisitor implements AnnotationValueVisitor<Object, Object> {

    protected Consumer<String> printer;
    protected String key;

    public CustomAnnotationValueVisitor(String key, Consumer<String> printer) {
        this.key = key;
        this.printer = printer;
    }

    public CustomAnnotationValueVisitor() {
        this(null, msg -> {
        });
    }

    protected void print(String message) {
        this.printer.accept(message);
    }

    @Override
    public Object visit(AnnotationValue annotationValue, Object o) {
        print(String.format("annotationValue Obj , key : %s", key));
        return annotationValue;
    }

    @Override
    public Object visit(AnnotationValue annotationValue) {
        print(String.format("annotationValue , key : %s", key));
        return annotationValue;
    }

    @Override
    public Object visitBoolean(boolean b, Object o) {
        print(String.format("visitBoolean  , Object  : %s , key : %s", o, key));
        return b;
    }

    @Override
    public Object visitByte(byte b, Object o) {
        print(String.format("visitByte  , Object  : %s , key : %s", o, key));
        return b;
    }

    @Override
    public Object visitChar(char c, Object o) {
        print(String.format("visitChar  , Object  : %s , key : %s", o, key));
        return c;
    }

    @Override
    public Object visitDouble(double v, Object o) {
        print(String.format("visitDouble  , Object  : %s , key : %s", o, key));
        return v;
    }

    @Override
    public Object visitFloat(float v, Object o) {
        print(String.format("visitFloat  , Object  : %s , key : %s", o, key));
        return v;
    }

    @Override
    public Object visitInt(int i, Object o) {
        print(String.format("visitInt  , Object  : %s , key : %s", o, key));
        return i;
    }

    @Override
    public Object visitLong(long l, Object o) {
        print(String.format("visitLong  , Object  : %s , key : %s", o, key));
        return l;
    }

    @Override
    public Object visitShort(short i, Object o) {
        print(String.format("visitShort  , Object  : %s , key : %s", o, key));
        return i;
    }

    @Override
    public Object visitString(String s, Object o) {
        print(String.format("visitString  , Object  : %s , key : %s", o, key));
        return s;
    }

    @Override
    public Object visitType(TypeMirror typeMirror, Object o) {
        print(String.format("visitType ,  value %s ,  Object  : %s , key : %s", typeMirror.toString(), o, key));
        TypeName typeName=TypeName.get(typeMirror);
        return typeName;
    }

    @Override
    public Object visitEnumConstant(VariableElement variableElement, Object o) {
        print(String.format("variableElement ,Object : %s , key : %s", o, key));
        return variableElement;
    }

    @Override
    public Object visitAnnotation(AnnotationMirror annotationMirror, Object o) {
        print(String.format("visitAnnotation ,Object : %s , key : %s .", o, key));
        return annotationMirror;
    }

    @Override
    public Object visitArray(List<? extends AnnotationValue> list, Object o) {
        print(String.format("visitArray key : %s", key));
        return list.stream().map(i -> i.accept(this, null)).collect(Collectors.toList());
    }

    @Override
    public Object visitUnknown(AnnotationValue annotationValue, Object o) {
        print(String.format("visitUnknown ,Object : %s , key : %s .", o, key));
        return annotationValue;
    }
}


