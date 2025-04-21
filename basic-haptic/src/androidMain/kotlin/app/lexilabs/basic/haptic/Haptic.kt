package app.lexilabs.basic.haptic

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK) as Vibration
            } else {
                VibrationWrapper(50)
            }

        /** a nominal vibration and is longer than [TICK] */
        public actual val CLICK: Vibration =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK) as Vibration
            } else {
                VibrationWrapper(100)
            }

        /** a moderate vibration and is longer than [CLICK] */
        public val HEAVY_CLICK: Vibration =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK) as Vibration
            } else {
                VibrationWrapper(150)
            }

        /** two rapid [CLICK] vibrations */
        public val DOUBLE_CLICK: Vibration =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK) as Vibration
            } else {
                VibrationWrapper(200)
            }

        /** a 250 millisecond vibration */
        public val BUZZ: Vibration =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createOneShot(250, 1) as VibrationEffect as Vibration
            } else {
                VibrationWrapper(500)
            }

    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    public actual fun vibrate(pattern: Vibration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            (context.getSystemService("vibrator") as Vibrator).vibrate(
                pattern as VibrationEffect
            )
        } else {
            @Suppress("DEPRECATION")
            (context.getSystemService("vibrator") as Vibrator).vibrate(
                (pattern as VibrationWrapper).value
            )
        }
    }
}