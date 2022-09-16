package org.paranora.mapstruct.java.metadata.creator.mapstruct.mapper;

import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.ParameterMeta;

import java.util.Arrays;
import java.util.List;


public class MapstructMapperInterfaceConverterMethodSourceParameterMetaCreator extends AbsMapstructInterfaceMethodParameterMetaCreator {


    @Override
    public List<ParameterMeta> create(ClassMeta source, InterfaceMeta parent, Class<?> clasz) {
        ParameterMeta sourceParameter = ParameterMeta.builder()
                .name("source")
                .typeName(parent.getSuperInterfaces().get(0).getGenericTypes().get(0))
                .build();

        return Arrays.asList(sourceParameter);
    }
}