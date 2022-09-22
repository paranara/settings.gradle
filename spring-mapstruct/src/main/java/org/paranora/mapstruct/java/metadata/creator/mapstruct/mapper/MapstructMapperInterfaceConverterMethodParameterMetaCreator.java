package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.ParameterMeta;

import java.util.Arrays;
import java.util.List;


public class MapstructMapperInterfaceConverterMethodParameterMetaCreator extends AbsMapstructInterfaceMethodParameterMetaCreator {

    @Override
    public List<ParameterMeta> creates(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        ParameterMeta parameter = ParameterMeta.builder()
                .name("source")
                .typeName(parent.getSuperInterfaces().get(0).getGenericTypes().get(0))
                .build();
        return Arrays.asList(parameter);
    }
}
