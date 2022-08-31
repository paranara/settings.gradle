package org.paranora.mapstruct.starter.core.java.metadata.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class InterfaceMeta extends BaseMeta {

    @Builder.Default
    protected List<MethodMeta> methods=new ArrayList<>();

    @Builder.Default
    protected List<InterfaceMeta> superInterfaces=new ArrayList<>();
}
