package app.lexilabs.basic.haptic

import kotlinx.browser.window

/**
 * Creates vibrations using [Vibration] presets which have been standardized across platforms.
 * @constructor Creates a reusable [Haptic] instance which can create multiple vibrations
 * @param context unused value except on Android.
 * @property DEFAULTS a set of [Vibration] values used across platforms
 * @see vibrate
 */
public actual class Haptic actual constructor(context: Any) {

    /**
     * Used to wrap the [Vibration] interface due to typealias constraints
     * @param value The length of the [Vibration] in milliseconds
     */
    public data class VibrationWrapper(val value: Int): Vibration

    /**
     * Contains default [Vibration] values
     * @property TICK a short vibration and is shorter than [CLICK]
     * @property CLICK a nominal vibration and is longer than [TICK]
     * @property HEAVY_CLICK a moderate vibration and is longer than [CLICK]
     * @property BUZZ a 250 millisecond vibration
     */
    @Suppress("MemberVisibilityCanBePrivate")
    public actual object DEFAULTS {

        /** a short vibration and is shorter than [CLICK] */
        public actual val TICK: Vibration = VibrationWrapper(30)
        /** a nominal vibration and is longer than [TICK] */
        public actual val CLICK: Vibration = VibrationWrapper(60)
        /** a moderate vibration and is longer than [CLICK] */
        public val HEAVY_CLICK: Vibration = VibrationWrapper(90)
        /** a 250 millisecond vibration */
        public val BUZZ: Vibration = VibrationWrapper(250)
    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern.
     * If you want to specify a duration, use [VibrationWrapper]
     * to pass the number of milliseconds as a [Vibration]
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    public actual fun vibrate(pattern: Vibration) {
        window.navigator.vibrate((pattern as VibrationWrapper).value)
    }
}