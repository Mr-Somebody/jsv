package com.chewords.jsv.annotation;
/*
 *
 * @Author Joey
 * @Date 25/03/2019 11:16:54
 * @Desc
 *
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface JsvName {
    String name();
}
