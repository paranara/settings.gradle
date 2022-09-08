package org.paranora.mapstruct.java.metadata.creator.mapstruct;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.java.metadata.creator.InterfaceMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.AnnotationMeta;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ObjectUtils;

import javax.lang.model.element.Modifier;
import java.util.Arrays;
import java.util.Optional;

public class MapstructMapperInterfaceMetaCreator implements InterfaceMetaCreator<ClassMeta> {

    @Override
    public InterfaceMeta create(ClassMeta source, Class<?> clasz) {
        if (null == source) return null;
        Optional<AnnotationMeta> annotationMeta = source.getAnnotations().stream().filter(a -> a.getTypeName().equals(TypeName.get(PMapper.class))).findFirst();
        TypeName sourceClassType=source.getTypeName();
        TypeName targetClassType=annotationMeta.get().getTypeName();
        InterfaceMeta meta = new InterfaceMeta();
        meta.setName(createInterfaceName(source,sourceClassType,targetClassType));
        meta.setAccessLevels(Arrays.asList(Modifier.PUBLIC));
        meta.setSuperInterfaces(Arrays.asList(
                InterfaceMeta.builder()
                        .packageName(Converter.class.getPackage().getName())
                        .name(Converter.class.getSimpleName())
                        .genericTypes(Arrays.asList(sourceClassType, TypeName.get(Staff.class)))
                        .build()
        ));

        return meta;
    }

    protected String createInterfaceName(ClassMeta source,TypeName sourceClassType,TypeName targetClassType) {
        String sourceName = source.getName().toLowerCase();
        String targetName = null;
        Optional<AnnotationMeta> annotationMeta = source.getAnnotations().stream().filter(a -> a.getTypeName().equals(TypeName.get(PMapper.class))).findFirst();
        if (annotationMeta.isPresent()) targetName = annotationMeta.get().getName();
        if (!ObjectUtils.isEmpty(targetName)) {
            String first = targetName.substring(0, 1);
            String rest = targetName.substring(1);
            targetName = String.format("%s%s", first, rest);
        }
        if (!ObjectUtils.isEmpty(targetName)) {
            return String.format("%sTo%sMapper", sourceName, targetName);
        }
        return null;
    }

}
