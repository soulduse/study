package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

@HellobootTest
class MemberRepositoryTest {
    @Autowired
    lateinit var memberRepository: MemberRepository
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun init() {
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS MEMBER (
                id INTEGER NOT NULL PRIMARY KEY,
                name VARCHAR(255),
                age INTEGER
            )
        """.trimIndent())
    }

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
