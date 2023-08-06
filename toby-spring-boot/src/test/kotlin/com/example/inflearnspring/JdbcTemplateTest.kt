package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

@HellobootTest
class JdbcTemplateTest {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun insertAndQuery() {
        jdbcTemplate.update("INSERT INTO MEMBER VALUES (?, ?, ?)", 1, "keesun", 20)
        jdbcTemplate.update("INSERT INTO MEMBER VALUES (?, ?, ?)", 2, "Kim", 30)

        val count: Long? = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM MEMBER", Long::class.java)
        assertThat(count).isEqualTo(2)
    }
}
