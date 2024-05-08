// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
}

tasks {
    register("installGitHook", Copy::class) {
        from(File(rootProject.rootDir, "scripts/git-hooks/pre-commit"))
        into(File(rootProject.rootDir, ".git/hooks"))
        fileMode = 777
    }
    getByPath(":app:preBuild").dependsOn(":installGitHook")
}
