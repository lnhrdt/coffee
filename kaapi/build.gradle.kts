import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.yarn.YarnTask

apply {
    plugin("com.moowork.node")
}

configure<NodeExtension> {
    version = Versions.node
    yarnVersion = Versions.yarn
    download = true
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}

tasks {
    val yarnInstallTask = getByName<YarnTask>("yarn_install")

    val yarnTestTask = getByName<YarnTask>("yarn_test") {
        setEnvironment(mapOf("CI" to true))
    }

    register("test") {
        dependsOn(yarnInstallTask)
        dependsOn(yarnTestTask)
        yarnTestTask.dependsOn(yarnInstallTask)
    }
}
