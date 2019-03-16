import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    kotlin("jvm")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        this.jvmTarget = Versions.jvmTarget
    }
}

configure<DependencyManagementExtension> {
    imports {
        mavenBom(SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {
    compile("io.arrow-kt:arrow-core:${Versions.arrowVersion}")
    compile("io.arrow-kt:arrow-data:${Versions.arrowVersion}")

    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}")
    compile("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    testCompile("com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}") {
        exclude(group = "org.jetbrains.kotlin")
    }

    compile("org.springframework.boot:spring-boot-starter-web")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    runtime("org.springframework.boot:spring-boot-devtools")
    testCompile("org.springframework.boot:spring-boot-starter-test"){
        exclude(group = "junit")
    }
    testCompile("org.junit.jupiter:junit-jupiter-api")
    testRuntime("org.junit.jupiter:junit-jupiter-engine")

    compile("org.springframework.boot:spring-boot-starter-jdbc")
    compile("io.github.codebandits:beak:${Versions.beakVersion}")
    compile("org.flywaydb:flyway-core")
    runtime("mysql:mysql-connector-java")
}
