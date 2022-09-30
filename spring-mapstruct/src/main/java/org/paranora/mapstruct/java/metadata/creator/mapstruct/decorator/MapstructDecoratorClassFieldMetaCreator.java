package org.paranora.mapstruct.java.metadata.creator.mapstruct.decorator;

import com.squareup.javapoet.TypeName;
import org.paranora.mapstruct.converter.MapstructConversionService;
import org.paranora.mapstruct.converter.MapstructMapperConversionService;
import org.paranora.mapstruct.java.metadata.creator.mapstruct.AbsMapstructFieldMetaCreator;
import org.paranora.mapstruct.java.metadata.entity.ClassMeta;
import org.paranora.mapstruct.java.metadata.entity.FieldMeta;
import org.paranora.mapstruct.java.metadata.entity.InterfaceMeta;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapstructDecoratorClassFieldMetaCreator extends AbsMapstructFieldMetaCreator<InterfaceMeta, ClassMeta> {

    @Override
    public FieldMeta create(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        return null;
    }

    @Override
    public List<FieldMeta> creates(InterfaceMeta source, ClassMeta parent, Class<?> clasz) {
        List<FieldMeta> fieldMetas = new ArrayList<>();
        fieldMetas.add(FieldMeta.builder()
                .name("mapperConversionService")
                .accessLevels(Arrays.asList(Modifier.PRIVATE))
                .typeName(TypeName.get(MapstructMapperConversionService.class))
                .build());

        fieldMetas.add(FieldMeta.builder()
                .name("conversionService")
                .accessLevels(Arrays.asList(Modifier.PRIVATE))
                .typeName(TypeName.get(MapstructConversionService.class))
                .build());
        return fieldMetas;
    }
}