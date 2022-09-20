package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.metadata.entity.AnnotationFieldMeta;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ObjectUtils;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MapstructMapperInterfaceMetaCreator extends AbsMapstructInterfaceMetaCreator {

    protected TypeName getSourceClassType(ClassMeta source) {
        return source.getTypeName();
    }

    protected TypeName getTargetClassType(ClassMeta source, AnnotationFieldMeta annotationFieldMeta) {
        return annotationFieldMeta.getTypeName();
    }

    protected AnnotationFieldMeta readAnnotationField(List<AnnotationMeta> annotations, Function<AnnotationMeta, Boolean> filter, String fieldName) {
        return annotations.stream()
                .filter(a -> filter.apply(a))
                .findFirst().get()
                .getFields()
                .stream().filter(f -> f.getName().equalsIgnoreCase(fieldName))
                .findFirst()
                .get();
    }

    protected AnnotationFieldMeta readAnnotationField(List<AnnotationMeta> annotations, Class annotationClass, String fieldName) {
        return readAnnotationField(annotations
                , (a) -> a.getTypeName().equals(TypeName.get(annotationClass))
                , fieldName);
    }

    protected String createInterfaceName(ClassMeta source, AnnotationFieldMeta annotationFieldMeta) {
        String sourceName = source.getName().toLowerCase();
        String targetName = null;
        if (annotationFieldMeta.getValue() instanceof TypeMirror) {
            targetName = annotationFieldMeta.getValue().toString();
            targetName = targetName.substring(targetName.lastIndexOf(".") + 1);
        }
        if (annotationFieldMeta.getValue() instanceof Class) {
            targetName = ((Class) annotationFieldMeta.getValue()).getSimpleName();
        }
        String first = targetName.substring(0, 1);
        String rest = targetName.substring(1);
        targetName = String.format("%s%s", first.toUpperCase(), rest.toLowerCase());
        if (!ObjectUtils.isEmpty(targetName)) {
            return createInterfaceName(sourceName, targetName);
        }
        return null;
    }

    protected String createInterfaceName(String sourceName, String targetName) {
        return String.format("%sTo%sMapper", sourceName, targetName);
    }

    protected String createPackageName(ClassMeta source){
        return String.format("org.paranora.mapstruct.mapper");
    }

    @Override
    public InterfaceMeta create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        if (null == source) return null;
        AnnotationFieldMeta annotationFieldMeta = readAnnotationField(source.getAnnotations(), PMapper.class, "target");
        TypeName sourceClassType = getSourceClassType(source);
        TypeName targetClassType = getTargetClassType(source, annotationFieldMeta);
        InterfaceMeta meta = new InterfaceMeta();
        meta.setName(createInterfaceName(source, annotationFieldMeta));
        meta.setPackageName(createPackageName(source));
        meta.setAccessLevels(Arrays.asList(Modifier.PUBLIC));
        meta.setSuperInterfaces(Arrays.asList(
                InterfaceMeta.builder()
                        .packageName(Converter.class.getPackage().getName())
                        .name(Converter.class.getSimpleName())
                        .genericTypes(Arrays.asList(sourceClassType, targetClassType))
                        .build()
        ));

        return meta;
    }
}
