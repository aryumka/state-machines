import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.9.22"
}

group = "aryumka"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
  testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
  testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
  testImplementation("io.kotest:kotest-framework-datatest:5.8.0")
  testImplementation("io.mockk:mockk:1.13.4")
  testImplementation("com.ninja-squad:springmockk:4.0.0")
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "21"
}
