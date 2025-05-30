plugins {
    glass(JAVA)
    glass(PUBLISHING)
    glass(SIGNING)
    spotless(GRADLE)
    spotless(JAVA)
}

group = "pers.ketikai.minecraft.spigot"
version = "0.1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.AZUL)
    }
}

glass {
    release.set(8)

    application {
        sugar {
            enabled.set(true)
        }
    }

    withCopyright()
    withMavenPom()

    withSourcesJar()
    withJavadocJar()

    withInternal()
    withShadow()

    withJUnitTest()
}

repositories {
    mavenLocal()
    aliyun()
    sonatype()
    sonatype(SNAPSHOT)
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    mavenCentral()
}

dependencies {
    @Suppress("VulnerableLibrariesLocal", "RedundantSuppression")
    compileOnly(libs.spigot.api)
    compileOnly(libs.minecraft.next.spigot)
    compileOnly(fileTree(File(projectDir, "libraries")))

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

tasks.processResources {
    val props =
        mapOf(
            "version" to project.version,
        )
    filesMatching(listOf("plugin.yml")) {
        expand(props)
    }
}

publishing {
    repositories {
        project(project)
    }
}
