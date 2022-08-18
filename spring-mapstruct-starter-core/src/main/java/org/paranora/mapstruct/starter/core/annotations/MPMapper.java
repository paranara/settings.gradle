package org.paranora.mapstruct.starter.core.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
//@Repeatable(MPMappers.class)
public @interface MPMapper {
    Class<?> target();

    String name() default "source";

    String packageName() default "mapstruct-plus";

    Class<?>[] uses() default {};
}
