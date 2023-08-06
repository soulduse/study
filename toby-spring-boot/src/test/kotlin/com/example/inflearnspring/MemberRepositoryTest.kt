package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@HellobootTest
class MemberRepositoryTest {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun findMemberFailed() {
        val member = memberRepository.findMember("keesun")
        assertThat(member).isNull()
    }

    @Test
    fun increaseAge() {
        assertThat(memberRepository.ageOf("keesun")).isEqualTo(0)
        memberRepository.increaseAge("keesun")
        assertThat(memberRepository.ageOf("keesun")).isEqualTo(1)
        memberRepository.increaseAge("keesun")
        assertThat(memberRepository.ageOf("keesun")).isEqualTo(2)
    }
}
