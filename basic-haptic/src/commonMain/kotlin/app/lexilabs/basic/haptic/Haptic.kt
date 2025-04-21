package app.lexilabs.basic.haptic

/**
 * Creates vibrations using [Vibration] presets which have been standardized across platforms.
 * @constructor Creates a reusable [Haptic] instance which can create multiple vibrations
 * @param context value must be [Context] on Android and can be [Any] for every other platform
 * @property DEFAULTS a set of [Vibration] values used across platforms
 * @see vibrate
 */
@DependsOnAndroidVibratePermission
public expect class Haptic(context: Any) {

    /**
     * Contains default [Vibration] values
     * Some platforms have more [Vibration] default options than others
     * The standard defaults are [TICK] and [CLICK]
     * @property TICK a short vibration and is shorter than [CLICK]
     * @property CLICK a nominal vibration and is longer than [TICK]
     */
    public object DEFAULTS {

        /** Creates a short vibration and is shorter than [CLICK] */
        public val TICK: Vibration

        /** Creates a nominal vibration and is longer than [TICK] */
        public val CLICK: Vibration
    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    public fun vibrate(pattern: Vibration)
}