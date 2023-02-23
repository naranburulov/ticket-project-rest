package com.cydeo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)                     //annotation will be applied to a method
@Retention(RetentionPolicy.RUNTIME)             //will work during the runtime
public @interface DefaultExceptionMessage {

    String defaultMessage() default "";         //by default the message will be "" (nothing)

}