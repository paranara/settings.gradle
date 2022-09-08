package org.paranora.mapstruct.annotations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PMappers {
    PMapper[] value();
}
