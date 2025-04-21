package app.lexilabs.basic.haptic

import android.Manifest
import android.content.Context
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
            VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK) as Vibration

        /** a nominal vibration and is longer than [TICK] */
        public actual val CLICK: Vibration =
            VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK) as Vibration

        /** a moderate vibration and is longer than [CLICK] */
        public val HEAVY_CLICK: Vibration =
            VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK) as Vibration

        /** two rapid [CLICK] vibrations */
        public val DOUBLE_CLICK: Vibration =
            VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK) as Vibration

        /** a 250 millisecond vibration */
        public val BUZZ: Vibration =
            VibrationEffect.createOneShot(250, 1) as VibrationEffect as Vibration

    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    public actual fun vibrate(pattern: Vibration) {
        (context.getSystemService("vibrator") as Vibrator).vibrate(
            pattern as VibrationEffect
        )
    }
}