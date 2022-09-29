package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructInterfaceMetaCreator;
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
import java.util.Optional;
import java.util.function.Function;

public class MapstructMapperInterfaceMetaCreator extends AbsMapstructInterfaceMetaCreator {

    protected TypeName getSourceClassType(ClassMeta source) {
        return source.getTypeName();
    }

    protected TypeName getTargetClassType(ClassMeta source) {
        AnnotationFieldMeta fieldMeta = readAnnotationField(source.getAnnotations(), PMapper.class, "target");
        TypeName tn = null;
        if (fieldMeta.getValue().getValue() instanceof TypeMirror) {
            tn = TypeName.get((TypeMirror) fieldMeta.getValue().getValue());
        }
        if (fieldMeta.getValue().getValue() instanceof Class) {
            tn = TypeName.get((Class) fieldMeta.getValue().getValue());
        }
        return tn;
    }

    protected AnnotationFieldMeta readAnnotationField(List<AnnotationMeta> annotations, Function<AnnotationMeta, Boolean> filter, String fieldName) {
        return annotations
                .stream()
                .filter(a -> filter.apply(a))
                .findFirst().get()
                .getFields()
                .values()
                .stream().filter(f -> f.getName().equalsIgnoreCase(fieldName))
                .findFirst()
                .get();
    }

    protected AnnotationFieldMeta readAnnotationField(List<AnnotationMeta> annotations, Class annotationClass, String fieldName) {
        return readAnnotationField(annotations
                , (a) -> a.getTypeName().equals(TypeName.get(annotationClass))
                , fieldName);
    }

    protected String createInterfaceName(ClassMeta source, InterfaceMeta meta) {
        Object targetType = meta.getSuperInterfaces().get(0).getGenericTypes().get(1);
        String sourceName = source.getName();
        String targetName = targetType.toString();
        targetName = targetName.substring(targetName.lastIndexOf(".") + 1);
        String first = targetName.substring(0, 1);
        String rest = targetName.substring(1);
        targetName = String.format("%s%s", first.toUpperCase(), rest);
        if (!ObjectUtils.isEmpty(targetName)) {
            return createInterfaceName(sourceName, targetName);
        }
        return null;
    }

    protected String createInterfaceName(String sourceName, String targetName) {
        return String.format("%sTo%sMapper", sourceName, targetName);
    }

    protected String createPackageName(ClassMeta source) {
        Optional<AnnotationMeta> opt = source.getAnnotations().stream().filter(a -> a.getName().equalsIgnoreCase(PMapper.class.getSimpleName())).findFirst();
        String packageName = PMapper.DefaultPackageName;
        if (opt.isPresent()) {
            Optional<AnnotationFieldMeta> fieldMetaOpt = opt.get().getFields().values().stream().filter(f -> f.getName().equalsIgnoreCase("packageName")).findFirst();
            if (fieldMetaOpt.isPresent()) {
                packageName = fieldMetaOpt.get().getValue().toString();
            }
        }
        return packageName;
    }

    @Override
    public InterfaceMeta create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        if (null == source) return null;
        InterfaceMeta meta = new InterfaceMeta();
        meta.setPackageName(createPackageName(source));
        meta.setAccessLevels(Arrays.asList(Modifier.PUBLIC));
        meta.setSuperInterfaces(createSuperInterfaces(source));
        meta.setName(createInterfaceName(source, meta));
        return meta;
    }

    protected List<InterfaceMeta> createSuperInterfaces(ClassMeta source) {
        TypeName sourceClassType = getSourceClassType(source);
        TypeName targetClassType = getTargetClassType(source);
        return Arrays.asList(
                InterfaceMeta.builder()
                        .packageName(Converter.class.getPackage().getName())
                        .name(Converter.class.getSimpleName())
                        .genericTypes(Arrays.asList(sourceClassType, targetClassType))
                        .build()
        );
    }
}
