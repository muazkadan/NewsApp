// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint").version("11.5.0")
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}

tasks {
    register("installGitHook", Copy::class) {
        from(File(rootProject.rootDir, "scripts/git-hooks/pre-commit"))
        into(File(rootProject.rootDir, ".git/hooks"))
        fileMode = 777
    }
    getByPath(":app:preBuild").dependsOn(":installGitHook")
}
