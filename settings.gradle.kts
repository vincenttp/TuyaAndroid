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

        // Tuya repositories for Smart SDK
        maven { url = uri("https://maven-other.tuya.com/repository/maven-releases/") }
        maven { url = uri("https://maven-other.tuya.com/repository/maven-commercial-releases/") }

        // Additional repositories
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
        maven { url = uri("https://developer.huawei.com/repo/") }
    }
}

rootProject.name = "TuyaSample"
include(":app")
 