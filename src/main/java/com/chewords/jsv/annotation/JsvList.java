package com.chewords.jsv.annotation;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 10:32:25
 * @Desc
 *
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface JsvList {
    String sep() default ",";
    Class type() default ArrayList.class;
    Class vType() default String.class;
}
