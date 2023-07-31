package com.example.config

import org.springframework.boot.context.annotation.ImportCandidates
import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyAutoConfigImportSelector(
    private val classLoader: ClassLoader,
) : DeferredImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        val candidates = ImportCandidates.load(MyAutoConfiguration::class.java, classLoader)
        return candidates.toList().toTypedArray()
    }
}
