package app.lexilabs.basic.haptic

/**
 * Indicates that the Android Permission for VIBRATE is missing from the manifest.
 * You can add it by including the following line of code in your AndroidManifest.xml file:
 *```
 * <uses-permission android:name="android.permission.VIBRATE" />
 * ```
 *
 */
@RequiresOptIn(message = "Depends on `Manifest.permission.VIBRATE`")
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class DependsOnAndroidVibratePermission
