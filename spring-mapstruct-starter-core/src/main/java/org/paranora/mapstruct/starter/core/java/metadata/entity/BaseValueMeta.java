package org.paranora.mapstruct.starter.core.java.metadata.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BaseValueMeta extends BaseMeta {

    protected Object value;
}
