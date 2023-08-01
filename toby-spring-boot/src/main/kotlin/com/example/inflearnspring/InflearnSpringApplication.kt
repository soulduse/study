package com.example.inflearnspring

import com.example.config.MySpringBootApplication
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@MySpringBootApplication
class InflearnSpringApplication

fun main(args: Array<String>) {
    runApplication<InflearnSpringApplication>(*args)
}
