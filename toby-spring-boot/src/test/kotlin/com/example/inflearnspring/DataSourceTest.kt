package com.example.inflearnspring

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.sql.DataSource

@HellobootTest
class DataSourceTest {

    @Autowired
    lateinit var dataSource: DataSource

    @Test
    fun connect() {
        val connect = dataSource.connection
        connect.close()
    }
}
