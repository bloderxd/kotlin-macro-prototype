plugins {
    kotlin("jvm") version "2.1.20"
}

group = "org.jetbrains.kotlin.compiler.plugin.template"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(kotlin("compiler"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}