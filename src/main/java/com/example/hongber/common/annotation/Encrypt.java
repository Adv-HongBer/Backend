package com.example.hongber.common.annotation;

import com.example.hongber.common.enumeration.EncryptType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypt {
    EncryptType value() default EncryptType.NONE;

    boolean selOpt() default false;
}