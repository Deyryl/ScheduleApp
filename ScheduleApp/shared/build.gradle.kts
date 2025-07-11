import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation("io.insert-koin:koin-core:4.1.0")
            implementation("io.insert-koin:koin-androidx-workmanager:4.1.0")
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation("io.insert-koin:koin-core:4.1.0")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
            implementation(libs.bundles.ktor)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            implementation("io.insert-koin:koin-core:4.1.0")
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.vnmhpractice.scheduleapp"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

