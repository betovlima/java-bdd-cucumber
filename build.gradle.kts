plugins {
    id("java")
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}


group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

repositories { mavenCentral() }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage")
    }

    testImplementation("io.cucumber:cucumber-java:7.16.1")
    testImplementation("io.cucumber:cucumber-spring:7.16.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.16.1")

    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.junit.platform:junit-platform-suite-api")
    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.26.3")
}

tasks.test {
    useJUnitPlatform()

    jvmArgs("-Dfile.encoding=UTF-8")

    testLogging {
        events("FAILED", "PASSED", "SKIPPED")
        showStandardStreams = true
    }
}

val isWindows = System.getProperty("os.name").lowercase().contains("win")
val cucumberJson = layout.buildDirectory.file("reports/cucumber/cucumber.json")

if (project.findProperty("importToJira")?.toString() == "true") {
    tasks.test { finalizedBy("importCucumberToJira") }
}
