import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("io.github.goooler.shadow") version "8.1.7"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "dev.piotrulla.newbieprotection"
version = "1.0.0"

repositories {
    mavenCentral()

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    // spigot api
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")

    // jda
    implementation("net.dv8tion:JDA:5.2.1") {
         exclude(module="opus-java")
    }

    // config system
    val okaeriConfigsVersion = "5.0.5"
    implementation("eu.okaeri:okaeri-configs-yaml-snakeyaml:${okaeriConfigsVersion}")
    implementation("eu.okaeri:okaeri-configs-serdes-commons:${okaeriConfigsVersion}")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:${okaeriConfigsVersion}")

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Xlint:deprecation")
    options.encoding = "UTF-8"
}

bukkit {
    main = "dev.piotrulla.simplediscordreward.SimpleDiscordReward"
    description = "Simple discord reward plugin!"
    prefix = "ptrlSimpleDiscordReward"
    version = "${project.version}"
    name = "ptrlSimpleDiscordReward"
    author = "Piotrulla"
    apiVersion = "1.13"

    commands {
        register("nagroda") {
            aliases = listOf("discord", "discordreward")
        }
    }
}

tasks {
    runServer {
        minecraftVersion("1.18.2")
    }
}

tasks.withType<ShadowJar> {
    archiveFileName.set("ptrlSimpleDiscordReward - ${project.version}.jar")

    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "org/checkerframework/**",
        "META-INF/**",
        "javax/**",
    )

    minimize()

    val prefix = "dev.piotrulla.newbieprotection.shared"

    listOf(
        "com.neovisionaries",
        "com.fasterxml",
        "net.dv8tion",
        "com.google",
        "org.apache",
        "eu.okaeri",
        "org.yaml",
        "google",
        "kotlin",
        "okhttp3",
        "okio",
        "gnu"
    ).forEach { pack ->
        relocate(pack, "$prefix.$pack")
    }
}