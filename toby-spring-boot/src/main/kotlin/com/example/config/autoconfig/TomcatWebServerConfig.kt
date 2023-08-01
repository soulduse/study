package com.example.config.autoconfig

import com.example.config.ConditionalMyOnClass
import com.example.config.MyAutoConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
class TomcatWebServerConfig(
    @Value("\${contextPath}")
    private val contextPathEnv: String,
) {

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean // 사용자가 등록한게 있다면 사용하고 없을 때 사용하도록 결정하는 어노테이션
    fun servletWebServerFactory(): ServletWebServerFactory {
        println("contextPath => $contextPathEnv")
        val factory = TomcatServletWebServerFactory().apply {
            this.contextPath = contextPathEnv
        }
        return factory
    }
}
