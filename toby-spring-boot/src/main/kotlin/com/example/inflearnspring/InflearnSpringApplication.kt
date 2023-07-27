package com.example.inflearnspring

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServer
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
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
    val applicationContext = object : AnnotationConfigWebApplicationContext() {
        @Suppress("ACCIDENTAL_OVERRIDE")
        override fun setClassLoader(classLoader: ClassLoader) {
            this.classLoader = classLoader
        }

        override fun onRefresh() {
            super.onRefresh()

            val serverFactory = this.getBean(ServletWebServerFactory::class.java)
            val dispatcherServlet = this.getBean(DispatcherServlet::class.java)
            val webServer: WebServer = serverFactory.getWebServer({ servletContext ->
                servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                    .addMapping("/*")
            })
            webServer.start()
        }
    }.apply {
        register(InflearnSpringApplication::class.java)
        refresh()
    }
}
