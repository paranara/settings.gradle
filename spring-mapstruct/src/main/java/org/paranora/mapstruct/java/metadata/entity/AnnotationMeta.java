package org.paranora.mapstruct.java.metadata.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AnnotationMeta extends BaseMeta {

    @Builder.Default
    protected Map<String, AnnotationFieldMeta> fields = new HashMap();

    public AnnotationFieldMeta field(Object fieldName) {
        return this.fields.get(fieldName);
    }

    public boolean containsField(Object fieldName) {
        return this.fields.containsKey(fieldName);
    }

    public void setField(AnnotationFieldMeta field) {
        this.fields.put(field.getName(), field);
    }
}
