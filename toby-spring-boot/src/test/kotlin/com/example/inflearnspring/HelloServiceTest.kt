package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Test
annotation class UnitTest

@UnitTest
annotation class FastUnitTest

class HelloServiceTest {
    @FastUnitTest
    fun simpleHelloService() {
        val helloService = SimpleHelloService()
        val ret = helloService.hello("Dave")

        assertThat(ret).isEqualTo("Hello, Dave")
    }

    @UnitTest
    fun helloDecorator() {
        val helloDecorator = HelloDecorator(object : HelloService {
            override fun hello(name: String): String {
                return "Hello, $name"
            }
        })
        val ret = helloDecorator.hello("Dave")

        assertThat(ret).isEqualTo("* Hello, Dave *")
    }
}
