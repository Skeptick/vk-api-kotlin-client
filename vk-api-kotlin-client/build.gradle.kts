sourceSets {
    main { java.srcDir("src/main/kotlin") }
    test { java.srcDir("src/test/kotlin") }
}

dependencies {
    val serializationVersion: String by extra
    val coroutinesVersion: String by extra
    val ktorVersion: String by extra
    val resultVersion: String by extra

    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("io.ktor:ktor-client:$ktorVersion")
    implementation("com.github.kittinunf.result:result:$resultVersion")
}