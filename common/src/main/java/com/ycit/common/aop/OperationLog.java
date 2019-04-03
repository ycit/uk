package com.ycit.common.aop;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {

    String moduleName() default "未知模块";

    String description() default "未定义";


}
