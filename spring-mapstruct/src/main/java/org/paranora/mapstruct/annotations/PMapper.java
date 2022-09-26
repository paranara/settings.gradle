package org.paranora.mapstruct.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME )
//@Repeatable(MPMappers.class)
public @interface PMapper {
    public static final String DefaultPackageName="org.paranora.mapstruct.starter.generated";

    public static final String ComponentModel="componentModel";

    public static final String NullValueCheckStrategy="nullValueCheckStrategy";

    public static final String NullValueMappingStrategy="nullValueMappingStrategy";

    Class<?> target();

    String name() default "source";

    String packageName() default PMapper.DefaultPackageName;

    Class<?>[] uses() default {};
}
