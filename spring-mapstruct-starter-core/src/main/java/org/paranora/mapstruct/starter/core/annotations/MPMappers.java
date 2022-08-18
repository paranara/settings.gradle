package org.paranora.mapstruct.starter.core.annotations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface MPMappers {
    MPMapper[] value();
}
