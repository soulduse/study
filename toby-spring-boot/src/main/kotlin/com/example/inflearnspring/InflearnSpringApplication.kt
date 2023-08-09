package com.example.inflearnspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import javax.annotation.PostConstruct

@SpringBootApplication
class InflearnSpringApplication(
    private val jdbcTemplate: JdbcTemplate,
) {
    @PostConstruct
    fun init() {
        jdbcTemplate.execute(
            """
            CREATE TABLE IF NOT EXISTS MEMBER (
                id INTEGER NOT NULL PRIMARY KEY,
                name VARCHAR(255),
                age INTEGER
            )
        """.trimIndent()
        )
    }
}

fun main(args: Array<String>) {
    runApplication<InflearnSpringApplication>(*args)
}
