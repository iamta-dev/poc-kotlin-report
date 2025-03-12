plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.serialization") version "1.9.25"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
}

group = "com.ktb.dcb"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://packages.confluent.io/maven/")}
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("net.sf.jasperreports:jasperreports:7.0.1")
    implementation("net.sf.jasperreports:jasperreports-pdf:7.0.1")
    implementation("commons-codec:commons-codec:1.16.0")
    implementation("org.apache.kafka:kafka_2.13:3.8.1")
    implementation("org.springframework.kafka:spring-kafka:3.3.2")
    implementation("io.confluent:kafka-avro-serializer:7.7.1")
    implementation("org.apache.avro:avro:1.11.3")
    implementation("org.apache.avro:avro-compiler:1.11.3")
    implementation("org.apache.avro:avro-ipc:1.11.3")
    implementation("com.github.avro-kotlin.avro4k:avro4k-core:2.1.1")
    implementation("software.amazon.awssdk:s3:2.20.0")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
