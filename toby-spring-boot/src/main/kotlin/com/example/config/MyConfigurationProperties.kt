package com.example.config

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MyConfigurationProperties(val prefix: String)
