package com.example.rh_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author RH
 * @date 2018/10/23
 */
@Target(ElementType.TYPE)//注解用在类上
@Retention(RetentionPolicy.SOURCE)//告诉编译器在源码阶段处理注解
public @interface AppRegisterGenerator {
    String packageName();

    Class<?> registerTemplate();
}
