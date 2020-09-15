buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }

    val kotlinVersion = "1.4.10"

    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath(kotlin("serialization", kotlinVersion))
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}