# Basic-Haptic
<img src="images/logo-icon.svg" alt="basic" height="240" style="float:right;"/> 

![GitHub License](https://img.shields.io/github/license/lexilabs-app/basic-haptic)
![GitHub Release Date](https://img.shields.io/github/release-date/Lexilabs-App/basic-haptic)
[![Latest Release](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-haptic?color=blue&label=latest)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-haptic)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-7f52ff.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

A Kotlin Multiplatform library to rapidly get vibration running across all your devices.

![badge-android](http://img.shields.io/badge/android-full_support-65c663.svg?style=flat)
![badge-iOS](http://img.shields.io/badge/iOS-full_support-65c663.svg?style=flat)
![badge-macOS](http://img.shields.io/badge/macOS-full_support-65c663.svg?style=flat)
![badge-watchOS](http://img.shields.io/badge/watchOS-full_support-65c663.svg?style=flat)
![badge-wasm](https://img.shields.io/badge/wasm-full_support-65c663.svg?style=flat)
![badge-javascript](https://img.shields.io/badge/javascript-full_support-65c663.svg?style=flat)

### How it works
Basic-Haptic uses each platform's native vibration libraries and patterns. For simplicity, these complex libraries have been whittled down into just two basic vibration modes: `CLICK` and `TICK`. Each platform-specific implementation has a library of additional default vibrations.

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
You'll need to pass a common value of  `context`, which is mostly so the Android side of things will work.
The Android implementation requires `Context`, while every other platform can receive `0` or `""`.
For an example of this technique, check out this [medium article](https://blog.hakz.app/contain-your-apps-memory-please-0c62819f8d7f).

```kotlin
/** inside your commonMain */

// Create a single Haptic instance to run all your vibrations
val hapticManager = Haptic(context)

// Vibrate the phone immediately using a click
hapticManager.vibrate(Haptic.DEFAULTS.CLICK)
```

## Advanced
If you're creating platform-specific implementations, you'll have access to a lot more `Haptic.DEFAULTS` values.
The best example of this is the `watchOS` implementation:
```kotlin
/** inside your watchosMain */

// Create a watchOS haptic instance
val hapticManager = Haptic("")

// Vibrate the phone using whichever unique watchOS vibration you like
hapticManager.vibrate(Haptic.DEFAULTS.UNDERWATER_DEPTH_CRITICAL_PROMPT)
```
