package com.example.inflearnspring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class HelloApiTest {

    @Test
    fun helloApi() {
        // http localhost:8080/hello?name=Dave
        // HTTPie
        val rest = TestRestTemplate()
        val response = rest.getForEntity("http://localhost:8080/hello?name={name}", String::class.java, "Dave")

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.headers.contentType.toString()).startsWith("text/plain;")
        assertThat(response.body).isEqualTo("Hello, Dave")
    }

    @Test
    fun failsHelloApi() {
        val rest = TestRestTemplate()
        val response = rest.getForEntity("http://localhost:8080/hello?name", String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
