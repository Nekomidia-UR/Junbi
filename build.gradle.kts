import godot.annotation.processor.classgraph.AnnotationProcessingMode
import godot.gradle.GodotLanguage

plugins {
    id("com.utopia-rise.godot-jvm") version "1.0.0-dev2"
}

repositories {
    mavenCentral()
}

godot {
    // --------- Setup ---------
    languages.set(setOf(GodotLanguage.KOTLIN))
    annotationProcessingMode.set(AnnotationProcessingMode.Inferred)
    godotProjectDirectory.set(file(".")) // only change this if the Godot project root is not this Gradle project directory

    // Enable to mark this Gradle project as a reusable library rather than a runnable Godot project.
    //isLibrary.set(true)

    // Enable coroutines integration with Godot signal/lifecycle callbacks.
    //isGodotCoroutinesEnabled.set(true)

    // --------- Toolchain ---------
    //javaVersion.set(17)
    //kotlinVersion.set("2.3.20")
    //scalaVersion.set("3.6.3")

    

    
}
