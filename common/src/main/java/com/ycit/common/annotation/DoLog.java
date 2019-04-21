package com.ycit.common.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoLog {

    String moduleName() default "未知模块";

    String description() default "未定义";

    String method() default "";


}
