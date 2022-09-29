package org.paranora.mapstruct.annotations;


import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.control.MappingControl;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//@Repeatable(MPMappings.class)
@Mapping(target = "")
public @interface PMapping {

    public static final String ClassFullName="org.paranora.mapstruct.annotations.PMapping";

    public static final String TARGET="target";

    public static final String SOURCE="source";

    public static final String EXPRESSION="expression";

    public static final String NEST="nest";

    boolean nest() default false;

    @AliasFor(
            annotation = Mapping.class
    )
    String target() default "";

    @AliasFor(
            annotation = Mapping.class
    )
    String dateFormat() default "";

    @AliasFor(
            annotation = Mapping.class
    )
    String numberFormat() default "";

    @AliasFor(
            annotation = Mapping.class
    )
    String constant() default "";

    @AliasFor(
            annotation = Mapping.class
    )
    String expression() default "";

    @AliasFor(
            annotation = Mapping.class
    )
    String defaultExpression() default "";

    @AliasFor(
            annotation = Mapping.class
    )
    boolean ignore() default false;

    @AliasFor(
            annotation = Mapping.class
    )
    Class<? extends Annotation>[] qualifiedBy() default {};

    @AliasFor(
            annotation = Mapping.class
    )
    String[] qualifiedByName() default {};

    @AliasFor(
            annotation = Mapping.class
    )
    Class<?> resultType() default void.class;

    @AliasFor(
            annotation = Mapping.class
    )
    String[] dependsOn() default {};

    @AliasFor(
            annotation = Mapping.class
    )
    String defaultValue() default "";

    @AliasFor(
            annotation = Mapping.class
    )
    NullValueCheckStrategy nullValueCheckStrategy() default NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;

    @AliasFor(
            annotation = Mapping.class
    )
    NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy() default NullValuePropertyMappingStrategy.SET_TO_NULL;

    @AliasFor(
            annotation = Mapping.class
    )
    Class<? extends Annotation> mappingControl() default MappingControl.class;

}
