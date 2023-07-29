package com.example.inflearnspring

import org.springframework.boot.SpringApplication
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet

@Configuration
@ComponentScan
class InflearnSpringApplication {
    @Bean
    fun servletWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }

    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        return DispatcherServlet()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(InflearnSpringApplication::class.java, *args)
//    runApplication<InflearnSpringApplication>(*args)
}
