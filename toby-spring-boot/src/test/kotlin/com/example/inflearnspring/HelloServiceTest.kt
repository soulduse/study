package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloServiceTest {
    @Test
    fun simpleHelloService() {
        val helloService = SimpleHelloService()
        val ret = helloService.hello("Dave")

        assertThat(ret).isEqualTo("Hello, Dave")
    }
}
