package org.paranora.mapstruct.java.metadata.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ClassMeta extends InterfaceMeta {

    @Builder.Default
    protected List<FieldMeta> fields=new ArrayList<>();
    protected ClassMeta superClass;
}
