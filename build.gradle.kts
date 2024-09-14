plugins {
    java
    kotlin("jvm") version "1.9.23"
    `maven-publish`
}

group = "com.naulian.processing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(fileTree("libs") { include("*.jar") })
    implementation(kotlin("stdlib-jdk8"))
    implementation(project("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven"){
                from(components["java"])
                groupId = "com.naulian"
                artifactId = "processing"
                version = "0.0.1-test01"
            }
        }
    }
}
