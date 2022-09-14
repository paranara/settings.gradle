package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.entity.*;

import javax.lang.model.element.Modifier;
import java.util.Arrays;

public class MapstructMapperInterfaceConverterMethodMetaCreator extends AbsMapstructInterfaceMethodMetaCreator {

    @Override
    public MethodMeta create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        MethodMeta meta = MethodMeta.builder()
                .accessLevels(Arrays.asList(Modifier.PUBLIC, Modifier.ABSTRACT))
                .returnType(parent.getSuperInterfaces().get(0).getGenericTypes().get(1))
                .name("convert")
                .build();
        return meta;
    }
}
