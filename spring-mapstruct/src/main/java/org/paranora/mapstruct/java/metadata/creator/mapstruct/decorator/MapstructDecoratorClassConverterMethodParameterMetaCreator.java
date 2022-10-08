package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;


import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;
import org.paranora.mapstruct.java.metadata.entity.ParameterMeta;

import java.util.Arrays;
import java.util.List;

public class MapstructDecoratorClassConverterMethodParameterMetaCreator extends AbsMapstructClassMethodParameterMetaCreator {

    @Override
    public List<ParameterMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {

        ParameterMeta parameter = ParameterMeta.builder()
                .name(crateMethodParameterName(source,parent,clasz))
                .typeName(source.getSuperInterfaces().get(0).getGenericTypes().get(0))
                .build();
        return Arrays.asList(parameter);
    }

    protected String crateMethodParameterName(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        return source.getMethods().get(0).getParameters().get(0).getName();
    }
}
