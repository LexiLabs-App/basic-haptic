# Basic-Haptic
<img src="images/logo-icon.svg" alt="basic" height="240" align="right"/> 

![GitHub License](https://img.shields.io/github/license/lexilabs-app/basic-haptic)
![GitHub Release Date](https://img.shields.io/github/release-date/lexilabs-app/basic-haptic)
[![Latest Release](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-haptic?color=blue&label=latest)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-haptic)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.21--RC-7f52ff.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

A Kotlin Multiplatform library to rapidly get Google AdMob running on Android and iOS

![badge-android](http://img.shields.io/badge/android-full_support-65c663.svg?style=flat)
![badge-iOS](http://img.shields.io/badge/iOS-full_support-65c663.svg?style=flat)
![badge-macOS](http://img.shields.io/badge/macOS-full_support-65c663.svg?style=flat)
![badge-watchOS](http://img.shields.io/badge/watchOS-full_support-65c663.svg?style=flat)
![badge-wasm](https://img.shields.io/badge/wasm-full_support-65c663.svg?style=flat)
![badge-javascript](https://img.shields.io/badge/javascript-full_support-65c663.svg?style=flat)

### How it works


## Installation

Add your dependencies from Maven
```toml
# in your 'libs.versions.toml' file
[versions]
basic = "+" # gets the latest version

[libraries]
basic-haptic = { module = "app.lexilabs.basic:basic-haptic", version.ref = "basic"}
```

then include the library in your gradle build
```kotlin
sourceSets {
    commonMain.dependencies {
        implementation(libs.lexilabs.basic.haptic)
    }
}
```

## Usage

