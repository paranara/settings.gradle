package org.paranora.mapstruct.annotations;


import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.control.MappingControl;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//@Repeatable(MPMappings.class)
public @interface PMapping {

    public static final String ClassFullName = "org.paranora.mapstruct.annotations.PMapping";

    public static final String TARGET = "target";

    public static final String SOURCE = "source";

    public static final String EXPRESSION = "expression";

    public static final String NEST = "nest";


    public static final String TARGETTYPE= "targetType";


    public static final String SOURCETYPE= "sourceType";


    boolean nest() default false;

    Class<?> sourceType() default void.class;

    Class<?> targetType() default void.class;

    String source() default "";

    String target() default "";

    String dateFormat() default "";

    String numberFormat() default "";

    String constant() default "";

    String expression() default "";

    String defaultExpression() default "";

    boolean ignore() default false;

    Class<? extends Annotation>[] qualifiedBy() default {};

    String[] qualifiedByName() default {};

    Class<?> resultType() default void.class;

    String[] dependsOn() default {};

    String defaultValue() default "";

    NullValueCheckStrategy nullValueCheckStrategy() default NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;

    NullValuePropertyMappingStrategy nullValuePropertyMappingStrategy() default NullValuePropertyMappingStrategy.SET_TO_NULL;

    Class<? extends Annotation> mappingControl() default MappingControl.class;

}
