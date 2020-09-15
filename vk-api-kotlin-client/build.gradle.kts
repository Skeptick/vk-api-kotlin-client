plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.gradle.maven-publish")
}

group = "tk.skeptick"
version = "0.2.3"

kotlin {
    val ideaActive = System.getProperty("idea.active") == "true"
    val os = org.gradle.internal.os.OperatingSystem.current()!!

    if (ideaActive) {
        jvm()
        js().nodejs()
        linuxX64()
    } else if (os.isMacOsX) {
        jvm()
        js().nodejs()
        linuxX64()
        macosX64()
        iosArm32()
        iosArm64()
        iosX64()
        tvosArm64()
        tvosX64()
        mingwX64()
    } else if (os.isWindows) {
        mingwX64()
    }

    sourceSets {
        commonMain {
            kotlin.srcDir("src/main/kotlin")
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
                api("io.ktor:ktor-client-core:1.4.0")
            }
        }
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.serialization.ExperimentalSerializationApi")
                useExperimentalAnnotation("kotlinx.serialization.InternalSerializationApi")
            }
        }
    }
}

publishing {
    publications.withType<MavenPublication>().all {
        pom {
            name.set("vk-api-kotlin-client")
            url.set("https://github.com/Skeptick/vk-api-kotlin-client")
            licenses {
                license {
                    name.set("The Apache Software License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    name.set("Danil Yudov")
                    email.set("skeptick13@gmail.com")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/Skeptick/vk-api-kotlin-client.git")
                developerConnection.set("scm:git:ssh://github.com:Skeptick/vk-api-kotlin-client.git")
                url.set("http://github.com/Skeptick/vk-api-kotlin-client/tree/master")
            }
        }
    }

    repositories {
        maven {
            name = "bintray"
            url = uri("https://api.bintray.com/maven/skeptick/maven/vk-api-kotlin-client/;publish=1")
            credentials {
                username = findProperty("bintrayUser")?.toString() ?: System.getenv("BINTRAY_USER")
                password = findProperty("bintrayApiKey")?.toString() ?: System.getenv("BINTRAY_APIKEY")
            }
        }
    }
}