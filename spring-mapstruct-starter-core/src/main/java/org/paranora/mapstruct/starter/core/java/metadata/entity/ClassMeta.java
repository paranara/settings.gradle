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
public class ClassMeta extends BaseMeta {

    @Builder.Default
    protected List<FieldMeta> fields=new ArrayList<>();
    protected ClassMeta superClass;
}
