package com.example.config.autoconfig

import com.example.config.MyConfigurationProperties
import org.springframework.stereotype.Component

@Component
@MyConfigurationProperties("server")
data class ServerProperties(
    var contextPath: String = "",
    var port: Int = 8080,
)
