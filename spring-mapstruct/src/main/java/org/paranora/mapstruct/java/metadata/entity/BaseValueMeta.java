package org.paranora.mapstruct.java.metadata.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseValueMeta extends BaseMeta {

    protected ValueMeta value;

    public ValueMeta value() {
        return this.value;
    }
}
