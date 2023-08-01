package com.example.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.AnnotatedTypeMetadata

class ConditionalTest {
    @Test
    fun conditional() {
        // true
        ApplicationContextRunner().apply {
            withUserConfiguration(Config1::class.java)
                .run { context ->
                    assertThat(context).hasSingleBean(MyBean::class.java)
                    assertThat(context).hasSingleBean(Config1::class.java)
                }
        }

        // false
        ApplicationContextRunner().apply {
            withUserConfiguration(Config2::class.java)
                .run { context ->
                    assertThat(context).doesNotHaveBean(MyBean::class.java)
                    assertThat(context).doesNotHaveBean(Config1::class.java)
                }
        }
    }

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    @Conditional(BooleanCondition::class)
    annotation class BooleanConditional(
        val value: Boolean = false,
    )

    @Configuration
    @BooleanConditional(true)
    class Config1 {
        @Bean
        fun myBean(): MyBean {
            return MyBean()
        }
    }

    @Configuration
    @BooleanConditional(false)
    class Config2 {
        @Bean
        fun myBean(): MyBean {
            return MyBean()
        }
    }

    class MyBean

    class BooleanCondition : Condition {
        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
            return metadata.getAnnotationAttributes(BooleanConditional::class.java.name)?.let {
                it["value"] as Boolean
            } ?: false
        }
    }
}
