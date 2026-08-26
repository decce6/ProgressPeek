import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("me.decce.transformingbase.gradle.transformingbase-common-conventions")
    id("fabric-loom") version "1.17-SNAPSHOT"
    id("com.gradleup.shadow")
    id("me.modmuss50.mod-publish-plugin")
}

fun prop(name: String) = if (hasProperty(name)) findProperty(name) as String else throw IllegalArgumentException("$name not found")
val modid = prop("modid")

val modSourceSet = sourceSets["mod-src"]

dependencies {
    minecraft("com.mojang:minecraft:${prop("deps.minecraft")}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.19.2")
}

tasks {
    named<Jar>("jar") {
        archiveClassifier = "slim"
    }

    named<ShadowJar>("shadowJar") {
        from (modSourceSet.output)
    }

    named<RemapJarTask>("remapJar") {
        dependsOn(shadowJar)
        inputFile = shadowJar.flatMap { it.archiveFile }
        archiveClassifier = ""
        manifest.attributes (
            "Automatic-Module-Name" to "me.decce.$modid.${prop("deps.platform")}"
        )
    }

    named<Copy>("buildAndCollect") {
        dependsOn(remapJar)
        from(remapJar.flatMap { it.archiveFile })
    }
}

publishMods {
    file = tasks.remapJar.get().archiveFile
}