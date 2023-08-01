package com.example.config.autoconfig

import com.example.config.ConditionalMyOnClass
import com.example.config.MyAutoConfiguration
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
class TomcatWebServerConfig {
    @Bean("tomcatWebServerFactory")
    fun servletWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }
}
