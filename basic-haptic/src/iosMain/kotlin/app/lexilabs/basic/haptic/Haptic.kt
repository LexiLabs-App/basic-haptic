package app.lexilabs.basic.haptic

import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle

/**
 * Creates vibrations using [Vibration] presets which have been standardized across platforms.
 * @constructor Creates a reusable [Haptic] instance which can create multiple vibrations
 * @param context unused value except on Android.
 * @see vibrate
 */
@Suppress("MemberVisibilityCanBePrivate")
public actual class Haptic actual constructor(context: Any) {

    /**
     * Used to wrap the [Vibration] interface due to typealias constraints
     *
     * @param value a [UIImpactFeedbackStyle] object
     */
    public data class VibrationIos(val value: UIImpactFeedbackStyle): Vibration

    /**
     * Contains default [Vibration] values
     * @property TICK a short vibration and is shorter than [CLICK]. The same as [LIGHT]
     * @property CLICK a nominal vibration and is longer than [TICK]. The same as [SOFT]
     * @property LIGHT equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleLight] vibration
     * @property SOFT equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleSoft] vibration
     * @property HEAVY equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy] vibration
     * @property MEDIUM equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium] vibration
     * @property RIGID equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleRigid] vibration
     */
    public actual object DEFAULTS {

        /** equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleLight] vibration */
        public val LIGHT: Vibration = VibrationIos(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)

        /** equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleSoft] vibration */
        public val SOFT: Vibration = VibrationIos(UIImpactFeedbackStyle.UIImpactFeedbackStyleSoft)

        /** equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy] vibration*/
        public val HEAVY: Vibration = VibrationIos(UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy)

        /** equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium] vibration */
        public val MEDIUM: Vibration = VibrationIos(UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium)

        /** equivalent to [UIImpactFeedbackStyle.UIImpactFeedbackStyleRigid] vibration */
        public val RIGID: Vibration = VibrationIos(UIImpactFeedbackStyle.UIImpactFeedbackStyleRigid)

        /** a short vibration and is shorter than [CLICK]. The same as [LIGHT] */
        public actual val TICK: Vibration = LIGHT

        /** a nominal vibration and is longer than [TICK]. The same as [SOFT] */
        public actual val CLICK: Vibration = SOFT
    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    public actual fun vibrate(pattern: Vibration) {
        val generator = UIImpactFeedbackGenerator((pattern as VibrationIos).value)
        generator.impactOccurred()
    }
}