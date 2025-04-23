package app.lexilabs.basic.haptic

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * Creates vibrations using [Vibration] presets which have been standardized across platforms.
 * @constructor Creates a reusable [Haptic] instance which can create multiple vibrations
 * @param context value must be [Context] on Android and can be [Any] for every other platform
 * @property DEFAULTS a set of [Vibration] values used across platforms
 * @see vibrate
 */
public actual class Haptic actual constructor(context: Any) {

    private val context: Context = context as Context

    /**
     * Used to wrap the [Vibration] interface due to typealias constraints
     *
     * ___Only required when using Build.VERSION.SDK_INT 26 or lower___
     *
     * @param value The length of the [Vibration] in milliseconds
     */
    public data class VibrationLong(val value: Long): Vibration

    /**
     * Used to wrap the [Vibration] interface due to typealias constraints
     *
     * @param value a [VibrationEffect] object
     */
    public data class VibrationAndroid(val value: VibrationEffect): Vibration

    /**
     * A set of default [Vibration] values
     * @property TICK a short vibration and is shorter than [CLICK]
     * @property CLICK a nominal vibration and is longer than [TICK]
     * @property HEAVY_CLICK a moderate vibration and is longer than [CLICK]
     * @property DOUBLE_CLICK two rapid [CLICK] vibrations
     * @property BUZZ a 250 millisecond vibration
     */
    @Suppress("MemberVisibilityCanBePrivate")
    public actual object DEFAULTS {

        /** a short vibration and is shorter than [CLICK] */
        public actual val TICK: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.Q..Int.MAX_VALUE -> {
                    VibrationAndroid(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK))
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationAndroid(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
                }
                else -> { VibrationLong(50)}
            }

        /** a nominal vibration and is longer than [TICK] */
        public actual val CLICK: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.Q..Int.MAX_VALUE -> {
                    VibrationAndroid(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationAndroid(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
                }
                else -> { VibrationLong(100)}
            }

        /** a moderate vibration and is longer than [CLICK] */
        public val HEAVY_CLICK: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.Q..Int.MAX_VALUE -> {
                    VibrationAndroid(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationAndroid(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
                }
                else -> { VibrationLong(150)}
            }

        /** two rapid [CLICK] vibrations */
        public val DOUBLE_CLICK: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.Q..Int.MAX_VALUE -> {
                    VibrationAndroid(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK))
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationAndroid(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                }
                else -> { VibrationLong(200)}
            }

        /** a 250 millisecond vibration */
        public val BUZZ: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.O..Int.MAX_VALUE -> {
                    VibrationAndroid(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                }
                else -> { VibrationLong(500)}
            }
    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    public actual fun vibrate(pattern: Vibration) {
        when(Build.VERSION.SDK_INT) {
            in Build.VERSION_CODES.O..Int.MAX_VALUE -> { vibrateUsingEffect(pattern as VibrationAndroid) }
            else -> { vibrateUsingWrapper(pattern as VibrationLong) }
        }
    }

    /**
     * Executes a vibration immediately based on a [VibrationEffect] pattern masked as a [Vibration]
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibrateUsingEffect(pattern: VibrationAndroid) {
        (context.getSystemService("vibrator") as Vibrator).vibrate(
            pattern.value
        )
    }

    /**
     * Executes a vibration immediately based on a [VibrationLong] pattern masked as a [Vibration]
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    @Suppress("DEPRECATION")
    private fun vibrateUsingWrapper(pattern: Vibration) {
        (context.getSystemService("vibrator") as Vibrator).vibrate(
            (pattern as VibrationLong).value
        )
    }
}