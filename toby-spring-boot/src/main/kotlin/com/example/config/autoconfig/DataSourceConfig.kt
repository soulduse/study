package com.example.config.autoconfig

import com.example.config.ConditionalMyOnClass
import com.example.config.EnableMyConfigurationProperties
import com.example.config.MyAutoConfiguration
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import java.sql.Driver
import javax.sql.DataSource

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties::class)
class DataSourceConfig {

    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean
    fun hikariDataSource(properties: MyDataSourceProperties): DataSource {
        return HikariDataSource().apply {
            driverClassName = properties.driverClassName
            jdbcUrl = properties.url
            username = properties.username
            password = properties.password
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun dataSource(properties: MyDataSourceProperties): DataSource {
        return SimpleDriverDataSource().apply {
            setDriverClass(Class.forName(properties.driverClassName) as Class<out Driver>)
            url = properties.url
            username = properties.username
            password = properties.password
        }
    }

}
