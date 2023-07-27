package com.example.inflearnspring

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServer
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

@Configuration
@ComponentScan
class InflearnSpringApplication

fun main(args: Array<String>) {
    val applicationContext = object : AnnotationConfigWebApplicationContext() {
        @Suppress("ACCIDENTAL_OVERRIDE")
        override fun setClassLoader(classLoader: ClassLoader) {
            this.classLoader = classLoader
        }

        override fun onRefresh() {
            super.onRefresh()
            val serverFactory = TomcatServletWebServerFactory()
            val webServer: WebServer = serverFactory.getWebServer({ servletContext ->
                servletContext.addServlet("dispatcherServlet", DispatcherServlet(this))
                    .addMapping("/*")
            })
            webServer.start()
        }
    }.apply {
        register(InflearnSpringApplication::class.java)
        refresh()
    }
}
