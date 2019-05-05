sourceSets {
    main { java.srcDir("src/main/kotlin") }
    test { java.srcDir("src/test/kotlin") }
}

dependencies {
    val serializationVersion: String by extra
    val coroutinesVersion: String by extra
    val ktorVersion: String by extra
    val resultVersion: String by extra

    api(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    api("io.ktor:ktor-client:$ktorVersion")
    api("com.github.kittinunf.result:result-coroutines:$resultVersion")
}