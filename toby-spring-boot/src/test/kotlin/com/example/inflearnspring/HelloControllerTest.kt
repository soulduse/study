package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HelloControllerTest {

    @Test
    fun hello() {
        val controller = HelloController(object : HelloService {
            override fun hello(name: String): String {
                return "Hello, $name"
            }
        })
        val name = "Dave"
        val ret = controller.hello(name)
        assertThat(ret).isEqualTo("Hello, $name")
    }

    @Test
    fun failsHelloController() {
        val controller = HelloController(object : HelloService {
            override fun hello(name: String): String {
                return "Hello, $name"
            }
        })
        val name = ""
        assertThrows<IllegalArgumentException> { controller.hello(name) }
    }
}
