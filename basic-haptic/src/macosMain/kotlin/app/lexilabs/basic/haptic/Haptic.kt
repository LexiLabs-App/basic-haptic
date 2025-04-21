package app.lexilabs.basic.haptic

import platform.AppKit.NSHapticFeedbackManager
import platform.AppKit.NSHapticFeedbackPatternAlignment
import platform.AppKit.NSHapticFeedbackPatternGeneric
import platform.AppKit.NSHapticFeedbackPatternLevelChange
import platform.AppKit.NSHapticFeedbackPerformanceTimeNow

/**
 * Creates vibrations using [Vibration] presets which have been standardized across platforms.
 * @constructor Creates a reusable [Haptic] instance which can create multiple vibrations
 * @param context unused value except on Android.
 * @property DEFAULTS a set of [Vibration] values used across platforms
 * @see vibrate
 */
@Suppress("MemberVisibilityCanBePrivate")
public actual class Haptic actual constructor(context: Any) {

    /**
     * Used to wrap the [Vibration] interface due to typealias constraints
     * @param value The id of the [Vibration] as a [Long]
     */
    public data class VibrationWrapper(val value: Long): Vibration

    /**
     * a set of default [Vibration] values
     * @property TICK a short vibration and is shorter than [CLICK]. Same as [ALIGNMENT]
     * @property CLICK a nominal vibration and is longer than [TICK]. Same as [GENERIC]
     * @property ALIGNMENT equivalent to [NSHapticFeedbackPatternAlignment]
     * @property GENERIC equivalent to [NSHapticFeedbackPatternGeneric]
     * @property LEVEL_CHANGE equivalent to [NSHapticFeedbackPatternLevelChange]
     */
    public actual object DEFAULTS {

        /** equivalent to [NSHapticFeedbackPatternAlignment] */
        public val ALIGNMENT: Vibration =
            VibrationWrapper(NSHapticFeedbackPatternAlignment)

        /** equivalent to [NSHapticFeedbackPatternLevelChange] */
        public val LEVEL_CHANGE: Vibration =
            VibrationWrapper(NSHapticFeedbackPatternLevelChange)

        /** equivalent to [NSHapticFeedbackPatternGeneric] */
        public val GENERIC: Vibration =
            VibrationWrapper(NSHapticFeedbackPatternGeneric)

        /** a short vibration and is shorter than [CLICK]. Same as [ALIGNMENT] */
        public actual val TICK: Vibration = ALIGNMENT

        /** a nominal vibration and is longer than [TICK]. Same as [GENERIC] */
        public actual val CLICK: Vibration = GENERIC
    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    public actual fun vibrate(pattern: Vibration) {
        val performer = NSHapticFeedbackManager.defaultPerformer
        performer.performFeedbackPattern(
            pattern = (pattern as VibrationWrapper).value,
            performanceTime = NSHapticFeedbackPerformanceTimeNow
        )
    }
}