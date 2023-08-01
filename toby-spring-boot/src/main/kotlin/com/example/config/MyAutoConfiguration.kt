package com.example.config

import org.springframework.context.annotation.Configuration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Configuration(proxyBeanMethods = false)
/**
 * proxyBeanMethods = false는 어떤 역할을 할까?
 * Proxy의 false를 주면 매번 다른 객체가 생성된다.
 * 기존에는 @Bean 메소드를 통해 생성되는 객체는 기본적으로 싱글턴 스코프를 가진다.
 */
annotation class MyAutoConfiguration
