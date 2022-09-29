package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;


import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.ParameterMeta;

import java.util.Arrays;
import java.util.List;

public class MapstructClassConverterMethodParameterMetaCreator extends AbsMapstructClassMethodParameterMetaCreator {

    @Override
    public List<ParameterMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        ParameterMeta parameter = ParameterMeta.builder()
                .name("source")
                .typeName(parent.getSuperInterfaces().get(0).getGenericTypes().get(0))
                .build();
        return Arrays.asList(parameter);
    }
}
