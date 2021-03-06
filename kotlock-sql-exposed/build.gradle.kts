plugins {
    id("com.github.darkxanter.library-convention")
}

description = "Kotlock Exposed SQL database provider"

dependencies {
    val exposedVersion: String by project
    val testcontainersVersion: String by project

    api(projects.kotlockCore)

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    testImplementation(projects.kotlockTest)
    testImplementation("org.postgresql:postgresql:42.3.4")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
}

//publishing {
//    publications {
//        named("mavenCentral") {
//
//        }
//    }
//}
