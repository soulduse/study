package com.example.config

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Conditional(MyOnClassCondition::class)
annotation class ConditionalMyOnClass(val value: String)

class MyOnClassCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val value = metadata.getAnnotationAttributes(ConditionalMyOnClass::class.java.name)?.let {
            it["value"] as String
        } ?: throw IllegalArgumentException("value is required")
        return ClassUtils.isPresent(value, context.classLoader)
    }
}
