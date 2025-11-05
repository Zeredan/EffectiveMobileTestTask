pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestCourses"
include(":app")
include(":feature")
include(":domain")
include(":data")
include(":core")
include(":feature:login")
include(":feature:main")
include(":core:ui")
include(":domain:courses")
include(":data:courses")
include(":data:courses:impl")
include(":data:courses:mock")
include(":core:common")
include(":core:networking")
include(":data:courses:impl:remote")
include(":data:courses:impl:remote:ktor")
include(":data:courses:impl:remote:retrofit")
include(":data:courses:impl:combined_selector")
include(":core:networking:ktor")
include(":core:networking:retrofit")
include(":data:courses:impl:local")
include(":domain:settings")
