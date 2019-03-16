buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${Versions.springBootVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlinVersion}")
    }
}

allprojects {
    group = "io.leonhardt.coffee"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://dl.bintray.com/codebandits/beak") }
        maven { setUrl("https://dl.bintray.com/kotlin/exposed") }
    }
}

tasks {
    register("downloadDependencies") {
        dependsOn(":kaapi:yarn_install")
        dependsOn(":latte:compileTestJava")
    }

    register("build") {
        dependsOn(":kaapi:yarn_run_build")
        dependsOn(":latte:assemble")
    }
}
