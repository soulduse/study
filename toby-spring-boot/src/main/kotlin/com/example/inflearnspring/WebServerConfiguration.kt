package com.example.inflearnspring

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 커스텀 구성정보 생성
 * 사용자가 직접 구성한 경우, 자동 구성으로 생성된 내용이 무시되고 먼저 사용된다.
 * @Bean을 직접 커스텀 한다면 자동 구성은 무시된다.
 */
@Configuration(proxyBeanMethods = false)
class WebServerConfiguration {
    @Bean
    fun customWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory().apply {
            port = 9090
        }
    }
}
