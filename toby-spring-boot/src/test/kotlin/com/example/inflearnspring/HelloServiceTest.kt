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
        val helloService = SimpleHelloService(memberRepository = getMemberRepositoryStub())
        val ret = helloService.hello("Dave")

        assertThat(ret).isEqualTo("Hello, Dave")
    }

    private fun getMemberRepositoryStub() = object : MemberRepository {
        override fun findMember(name: String): Member {
            return Member(1, name, 0)
        }

        override fun increaseAge(name: String) {
        }

        override fun ageOf(name: String): Int {
            return 0
        }
    }

    @UnitTest
    fun helloDecorator() {
        val helloDecorator = HelloDecorator(object : HelloService {
            override fun hello(name: String): String {
                return "Hello, $name"
            }

            override fun ageOf(name: String): Int {
                return "Hello, $name".length
            }
        })
        val ret = helloDecorator.hello("Dave")

        assertThat(ret).isEqualTo("* Hello, Dave *")
    }
}
