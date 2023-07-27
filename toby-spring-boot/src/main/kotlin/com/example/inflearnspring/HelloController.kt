package com.example.inflearnspring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping
class HelloController(
    private val helloService: HelloService,
) {

    @GetMapping("/hello")
    @ResponseBody
    fun hello(name: String): String {
        return helloService.hello(name)
    }
}
