package com.example.inflearnspring

import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * RestController is a combination of @Controller and @ResponseBody
 * @Controller has @Component annotation, so it is a bean
 */
@RestController
class HelloController(
    private val helloService: HelloService,
    private val applicationContext: ApplicationContext,
) {

    init {
        println("applicationContext = $applicationContext")
    }

    @GetMapping("/hello")
    fun hello(name: String): String {
        return helloService.hello(name)
    }
}
