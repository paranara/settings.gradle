package org.paranora.mapstruct.starter.processor;

import lombok.Getter;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Getter
public abstract class AbsProcessor extends AbstractProcessor {

    protected Filer filer;
    protected Messager messager;
    protected Elements elementUtils;
    protected Types typesUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        elementUtils = processingEnvironment.getElementUtils();
        typesUtils = processingEnvironment.getTypeUtils();
    }

    protected boolean isPresentAnnotation(TypeElement annotation,String presentAnnotationName) {
        return presentAnnotationName.contentEquals(annotation.getQualifiedName());
    }

    protected void processPresentAnnotation(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment, Class<?> annotationClass, Consumer<? super  Element> consumer) {
        annotations.stream().filter(annotation -> isPresentAnnotation(annotation, annotationClass.getName())).forEach(typeElement -> {
            roundEnvironment.getElementsAnnotatedWith(typeElement).stream().forEach(element -> {
                consumer.accept(element);
            });
        });
    }

    protected Optional<? extends AnnotationMirror> getAnnotationMirror(Element element, Class<?> clazz) {
        return element.getAnnotationMirrors().stream().filter(t->t.getAnnotationType().toString().equalsIgnoreCase(clazz.getName())).findFirst();
    }

    protected AnnotationValue getAnnotationValue(AnnotationMirror annotationMirror, String key) {
        Optional<? extends Map.Entry<? extends ExecutableElement, ? extends AnnotationValue>> entry= annotationMirror.getElementValues().entrySet().stream().filter(es->es.getKey().getSimpleName().toString().equalsIgnoreCase(key)).findFirst();
        if(entry.isPresent()){
            return entry.get().getValue();
        }
        return null;
    }

    protected void processAnnotationMirrorFields(AnnotationMirror annotationMirror, Consumer<Map.Entry<? extends ExecutableElement, ? extends AnnotationValue>> consumer) {
        annotationMirror.getElementValues().entrySet().stream().forEach(en->{
            consumer.accept(en);
        });
    }

    protected void processClassFields(Element element, Consumer<? super  VariableElement> consumer) {
        TypeMirror typeMirror = element.asType();
        ElementKind kind = element.getKind();
        print("typeMirror = " + typeMirror.toString());
        print("kind = " + kind.toString());
        if (ElementKind.CLASS == kind) {
            element.getEnclosedElements().stream().filter(e -> ElementKind.FIELD == e.getKind()).forEach(e -> {
                consumer.accept((VariableElement) e);
            });
        }
    }

    protected String prefix(){return "";}

    protected void print(String message) {
        print("%s",message);
    }

    protected void print(String format,Object... message) {
        messager.printMessage(Diagnostic.Kind.NOTE, String.format(prefix()+format,message));
    }

}
