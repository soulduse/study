package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@HellobootTest
class HelloServiceAgeTest {
    @Autowired
    lateinit var helloService: HelloService
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun hello() {
        val name = "keesun"
        repeat(10) { count ->
            helloService.hello(name)
            assertThat(memberRepository.ageOf(name)).isEqualTo(count + 1)
        }
    }
}
