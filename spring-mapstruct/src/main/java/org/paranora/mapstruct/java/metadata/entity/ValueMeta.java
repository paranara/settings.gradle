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

    public Object value() {
        return this.value;
    }

    public <T> T value(Class<T> claz) {
        return (T) value();
    }

}
