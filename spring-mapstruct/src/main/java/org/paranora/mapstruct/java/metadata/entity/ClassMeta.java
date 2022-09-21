package org.paranora.mapstruct.java.metadata.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ClassMeta extends InterfaceMeta {

    @Builder.Default
    protected Map<String,FieldMeta> fields=new HashMap<>();
    protected ClassMeta superClass;
}
