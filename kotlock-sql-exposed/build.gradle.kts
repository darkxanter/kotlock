plugins {
    id("com.github.darkxanter.library-convention")
}

dependencies {
    val exposedVersion: String by project

    implementation(projects.kotlockCore)

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    testImplementation(projects.kotlockTest)
    testImplementation("org.postgresql:postgresql:42.2.24")
}