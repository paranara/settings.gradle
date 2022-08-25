package org.paranora.mapstruct.starter.core.annotations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PMappers {
    PMapper[] value();
}
