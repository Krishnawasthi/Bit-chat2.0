/**
 * Indian Mesh — Offline Peer-to-Peer Mesh Messaging for Android
 *
 * Root settings file. Declares every Gradle module in the project and
 * configures the dependency resolution to use the version catalog
 * located at gradle/libs.versions.toml.
 */

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

rootProject.name = "IndianMesh"

// ── Application entry point ──────────────────────────────────────────
include(":app")

// ── Core utilities (pure Java, no Android framework dependency) ──────
include(":core")

// ── Domain layer (pure Java business logic) ─────────────────────────
include(":domain")

// ── Data layer (repository implementations) ─────────────────────────
include(":data")

// ── Database module (Room entities, DAOs, migrations) ───────────────
include(":database")

// ── Cryptography module (encryption, keys, signatures) ──────────────
include(":crypto")

// ── Network transport abstraction ───────────────────────────────────
include(":network")

// ── Bluetooth LE + Classic transport ────────────────────────────────
include(":bluetooth")

// ── Wi-Fi Direct + LAN transport ────────────────────────────────────
include(":wifi")

// ── Mesh routing, relay, and discovery ──────────────────────────────
include(":mesh")

// ── UI module (Fragments, ViewModels, navigation) ───────────────────
include(":ui")

// ── Shared test utilities and fakes ─────────────────────────────────
include(":testing")
