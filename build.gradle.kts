// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("compose_version", "1.3.0-beta01")
    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        val navVersion = "2.5.2"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint").version("10.2.0")
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
    register("installGitHook", Copy::class) {
        from(File(rootProject.rootDir, "scripts/git-hooks/pre-commit"))
        into(File(rootProject.rootDir, ".git/hooks"))
        fileMode = 777
    }
    getByPath(":app:preBuild").dependsOn(":installGitHook")
}



