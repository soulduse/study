package com.example.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ConfigurationTest {
    @Test
    fun configuration() {
        val myConfig = MyConfig()
        val bean1 = myConfig.bean1()
        val bean2 = myConfig.bean2()

        assertThat(bean1.common).isNotSameAs(bean2.common)

        val ac = AnnotationConfigApplicationContext().apply {
            register(MyConfig::class.java)
            refresh()
        }

        val secondBean1 = ac.getBean(Bean1::class.java)
        val secondBean2 = ac.getBean(Bean2::class.java)

        assertThat(secondBean1.common).isSameAs(secondBean2.common)
    }

    @Test
    fun proxyCommonMethod() {
        val myConfigProxy = MyConfigProxy()
        val bean1 = myConfigProxy.bean1()
        val bean2 = myConfigProxy.bean2()

        assertThat(bean1.common).isSameAs(bean2.common)
    }

    /**
     * proxy를 흉내낸 클래스. 기존에 없으면 새로 생성하고, 있으면 기존 것을 사용한다.
     */
    class MyConfigProxy : MyConfig() {
        private var common: Common? = null
        override fun common(): Common {
            if (this.common == null) {
                this.common = super.common()
            }
            return this.common!!
        }
    }

    @Configuration
    class MyConfig {
        @Bean
        fun common() = Common()

        @Bean
        fun bean1() = Bean1(common())

        @Bean
        fun bean2() = Bean2(common())
    }

    class Bean1(val common: Common)

    class Bean2(val common: Common)

    class Common

    // Bean1 <-- Common
    // Bean2 <-- Common
}
