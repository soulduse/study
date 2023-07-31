package com.example.inflearnspring

import com.example.config.MySpringBootApplication
import org.springframework.boot.runApplication

@MySpringBootApplication
class InflearnSpringApplication

fun main(args: Array<String>) {
    runApplication<InflearnSpringApplication>(*args)
}
