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
     * ___Only required when using Build.VERSION.SDK_INT 28 or lower___
     *
     * @param value The length of the [Vibration] in milliseconds
     */
    public data class VibrationWrapper(val value: Long): Vibration

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
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK) as Vibration
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE) as Vibration
                }
                else -> { VibrationWrapper(50)}
            }

        /** a nominal vibration and is longer than [TICK] */
        public actual val CLICK: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.Q..Int.MAX_VALUE -> {
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK) as Vibration
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE) as Vibration
                }
                else -> { VibrationWrapper(100)}
            }

        /** a moderate vibration and is longer than [CLICK] */
        public val HEAVY_CLICK: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.Q..Int.MAX_VALUE -> {
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK) as Vibration
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE) as Vibration
                }
                else -> { VibrationWrapper(150)}
            }

        /** two rapid [CLICK] vibrations */
        public val DOUBLE_CLICK: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.Q..Int.MAX_VALUE -> {
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK) as Vibration
                }
                in Build.VERSION_CODES.O..Build.VERSION_CODES.P -> {
                    VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE) as Vibration
                }
                else -> { VibrationWrapper(200)}
            }

        /** a 250 millisecond vibration */
        public val BUZZ: Vibration =
            when(Build.VERSION.SDK_INT) {
                in Build.VERSION_CODES.O..Int.MAX_VALUE -> {
                    VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE) as Vibration
                }
                else -> { VibrationWrapper(500)}
            }
    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    public actual fun vibrate(pattern: Vibration) {
        when(Build.VERSION.SDK_INT) {
            in Build.VERSION_CODES.O..Int.MAX_VALUE -> { vibrateUsingEffect(pattern) }
            else -> { vibrateUsingWrapper(pattern) }
        }
    }

    /**
     * Executes a vibration immediately based on a [VibrationEffect] pattern masked as a [Vibration]
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibrateUsingEffect(pattern: Vibration) {
        (context.getSystemService("vibrator") as Vibrator).vibrate(
            pattern as VibrationEffect
        )
    }

    /**
     * Executes a vibration immediately based on a [VibrationWrapper] pattern masked as a [Vibration]
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    @Suppress("DEPRECATION")
    private fun vibrateUsingWrapper(pattern: Vibration) {
        (context.getSystemService("vibrator") as Vibrator).vibrate(
            (pattern as VibrationWrapper).value
        )
    }
}