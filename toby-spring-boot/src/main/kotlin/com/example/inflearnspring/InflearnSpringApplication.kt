package com.example.inflearnspring

import org.springframework.boot.SpringApplication

@MySpringBootAnnotation
class InflearnSpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(InflearnSpringApplication::class.java, *args)
//    runApplication<InflearnSpringApplication>(*args)
}
