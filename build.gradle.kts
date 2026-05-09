plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.rabbitmq:amqp-client:5.30.0")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.12")

    implementation("com.rabbitmq:amqp-client:5.20.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

tasks.test {
    useJUnitPlatform()
}