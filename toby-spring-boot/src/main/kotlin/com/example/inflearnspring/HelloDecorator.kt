package com.example.inflearnspring

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class HelloDecorator(
    private val helloService: HelloService,
) : HelloService {

    override fun hello(name: String): String {
        return "* ${helloService.hello(name)} *"
    }
}
