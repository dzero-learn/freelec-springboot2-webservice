package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //매개변수 선언에 사용
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 유지:실핼 후에도 유지되어 해당 어노테이션 사용 가능
public @interface LoginUser { // 마커 어노테이션
}

