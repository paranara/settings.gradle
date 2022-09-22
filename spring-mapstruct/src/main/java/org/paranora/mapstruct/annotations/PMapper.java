package org.paranora.mapstruct.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME )
//@Repeatable(MPMappers.class)
public @interface PMapper {
    Class<?> target();

    String name() default "source";

    String packageName() default "org.paranora.mapstruct.starter.generated";

    Class<?>[] uses() default {};
}
