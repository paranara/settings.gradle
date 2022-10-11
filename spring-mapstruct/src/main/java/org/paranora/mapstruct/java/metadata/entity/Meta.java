package org.paranora.mapstruct.java.metadata.entity;

import com.squareup.javapoet.TypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Meta {
    protected String packageName;
    protected String name;
    protected TypeName typeName;
    protected Meta parent;
    protected TypeName contentType;

    public TypeName type() {
        return this.typeName;
    }
}
