package com.example.inflearnspring

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.sql.DataSource

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [InflearnSpringApplication::class])
@TestPropertySource("classpath:/application.properties")
class DataSourceTest {

    @Autowired
    lateinit var dataSource: DataSource

    @Test
    fun connect() {
        val connect = dataSource.connection
        connect.close()
    }
}
