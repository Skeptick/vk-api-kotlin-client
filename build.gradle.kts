buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }

    val kotlinVersion = "1.3.72"

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