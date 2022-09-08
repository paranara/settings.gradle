package org.paranora.mapstruct.java.metadata.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ValueMeta extends Meta {
    protected Object value;

}
