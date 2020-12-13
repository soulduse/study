val projectGroup: String by project
val projectVersion: String by project
val kotlinVersion: String by project
val ktlintVersion: String by project

plugins {
    val kotlinVersion = "1.4.10"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

allprojects {
    group = projectGroup
    version = projectVersion

    repositories {
        jcenter()
        mavenCentral()
    }
}

subprojects {

    apply {
        plugin("kotlin")
    }

    val ktlint by configurations.creating

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        ktlint("com.pinterest:ktlint:$ktlintVersion")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks {
        val outputDir = "${project.buildDir}/reports/ktlint"
        val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

        val ktlintCheck by creating(JavaExec::class) {
            inputs.files(inputFiles)
            outputs.dir(outputDir)

            description = "Check Kotlin code style."
            classpath = ktlint
            main = "com.pinterest.ktlint.Main"
            args = listOf("src/**/*.kt")
        }

        val ktlintFormat by creating(JavaExec::class) {
            inputs.files(inputFiles)
            outputs.dir(outputDir)

            description = "Fix Kotlin code style deviations."
            classpath = ktlint
            main = "com.pinterest.ktlint.Main"
            args = listOf("-F", "src/**/*.kt")
        }
    }
}
