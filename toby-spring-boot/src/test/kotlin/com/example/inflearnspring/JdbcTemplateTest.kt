package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

@HellobootTest
class JdbcTemplateTest {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun init() {
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS MEMBER (
                id INTEGER NOT NULL PRIMARY KEY,
                name VARCHAR(255)
            )
        """.trimIndent())
    }

    @Test
    fun insertAndQuery() {
        jdbcTemplate.update("INSERT INTO MEMBER VALUES (?, ?)", 1, "keesun")
        jdbcTemplate.update("INSERT INTO MEMBER VALUES (?, ?)", 2, "Kim")

        val count: Long? = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM MEMBER", Long::class.java)
        assertThat(count).isEqualTo(2)
    }
}
