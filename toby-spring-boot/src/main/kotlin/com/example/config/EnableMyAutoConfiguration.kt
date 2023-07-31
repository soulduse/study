package com.example.config

import com.example.config.autoconfig.DispatcherConfig
import com.example.config.autoconfig.TomcatWebServerConfig
import org.springframework.context.annotation.Import

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Import(DispatcherConfig::class, TomcatWebServerConfig::class)
annotation class EnableMyAutoConfiguration
