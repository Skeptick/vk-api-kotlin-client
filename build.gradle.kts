import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.jetbrains.kotlinx.serialization.gradle.SerializationGradleSubplugin

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }

    val kotlinVersion: String by extra
    val bintrayVersion: String by extra
    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath(kotlin("serialization", kotlinVersion))
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:$bintrayVersion")
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://dl.bintray.com/kittinunf/maven")
    }
}

subprojects {
    apply<KotlinPlatformJvmPlugin>()
    apply<JavaLibraryPlugin>()
    apply<SerializationGradleSubplugin>()
    apply<MavenPublishPlugin>()
    apply<BintrayPlugin>()

    val artifactPublish: String by extra
    val artifactGroupId: String by extra
    version = artifactPublish
    group = artifactGroupId

    configure<PublishingExtension> {
        val sourceSets: SourceSetContainer by project
        val sourcesJar by tasks.registering(Jar::class) {
            from(sourceSets["main"].allSource)
            archiveClassifier.set("sources")
        }

        publications {
            register(project.name, MavenPublication::class) {
                from(components["java"])
                artifact(sourcesJar.get())
                groupId = artifactGroupId
                artifactId = project.name
                version = artifactPublish

                pom {
                    licenses {
                        license {
                            name.set("Apache-2.0")
                            url.set("https://opensource.org/licenses/Apache-2.0")
                        }
                    }
                }
            }
        }
    }

    configure<BintrayExtension> {
        val bintrayUser: String? by project
        val bintrayKey: String? by project
        
        user = bintrayUser
        key = bintrayKey
        setPublications(project.name)
        publish = true

        pkg.apply {
            repo = "maven"
            name = project.name
            version.apply { name = artifactPublish }
        }
    }
}