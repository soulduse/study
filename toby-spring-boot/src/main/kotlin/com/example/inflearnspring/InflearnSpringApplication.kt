package com.example.inflearnspring

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServer
import org.springframework.context.support.registerBean
import org.springframework.web.context.support.GenericWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

fun main(args: Array<String>) {
    val applicationContext = GenericWebApplicationContext().apply {
        registerBean<SimpleHelloService>()
        registerBean<HelloController>()
        refresh()
    }

    val serverFactory = TomcatServletWebServerFactory()
    val webServer: WebServer = serverFactory.getWebServer({ servletContext ->
        servletContext.addServlet("dispatcherServlet", DispatcherServlet(applicationContext))
            .addMapping("/*")
    })
    webServer.start()
}
