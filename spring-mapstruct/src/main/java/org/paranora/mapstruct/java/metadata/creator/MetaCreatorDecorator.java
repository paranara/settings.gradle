package org.paranora.mapstruct.java.metadata.creator;

import org.paranora.mapstruct.java.metadata.entity.Meta;

public class MetaCreatorDecorator<S extends Object, TP extends Meta> implements MetaCreator<S, Meta, TP> {

    protected MetaCreator creator;

    public MetaCreatorDecorator(MetaCreator metaCreator) {
        this.creator = metaCreator;
    }


    @Override
    public Meta create(S source, TP parent, Class<?> clasz) {
        if (null != this.creator) {
            return this.creator.create(source, parent, clasz);
        }
        return null;
    }
}
