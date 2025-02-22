/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.io.appium.java.client)
    api(libs.org.testng.testng)
    api(libs.org.seleniumhq.selenium.selenium.support)
    api(libs.commons.io.commons.io)
    api(libs.com.fasterxml.jackson.core.jackson.databind)
    api(libs.org.apache.commons.commons.lang3)

    testImplementation(libs.org.junit.jupiter.junit.jupiter.api)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.engine)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.params)
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = "BorzoClient"
java.sourceCompatibility = JavaVersion.VERSION_16

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
