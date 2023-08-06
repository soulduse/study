package com.example.inflearnspring

import com.example.config.MySpringBootApplication
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.JdbcTemplate
import javax.annotation.PostConstruct

@MySpringBootApplication
class InflearnSpringApplication(
    private val jdbcTemplate: JdbcTemplate,
) {
    @PostConstruct
    fun init() {
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS MEMBER (
                id INTEGER NOT NULL PRIMARY KEY,
                name VARCHAR(255),
                age INTEGER
            )
        """.trimIndent())
    }
}

fun main(args: Array<String>) {
    runApplication<InflearnSpringApplication>(*args)
}
