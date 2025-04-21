package app.lexilabs.basic.haptic

import platform.WatchKit.WKHapticType
import platform.WatchKit.WKInterfaceDevice

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
     * Contains the [Vibration] values
     * @property TICK a short vibration and is shorter than [CLICK]
     * @property CLICK a nominal vibration and is longer than [TICK]
     * @property SUCCESS equivalent to [WKHapticType.WKHapticTypeSuccess]
     * @property FAILURE equivalent to [WKHapticType.WKHapticTypeFailure]
     * @property START equivalent to [WKHapticType.WKHapticTypeStart]
     * @property STOP equivalent to [WKHapticType.WKHapticTypeStop]
     * @property RETRY equivalent to [WKHapticType.WKHapticTypeRetry]
     * @property DIRECTION_DOWN equivalent to [WKHapticType.WKHapticTypeDirectionDown]
     * @property DIRECTION_UP equivalent to [WKHapticType.WKHapticTypeDirectionUp]
     * @property NAVIGATION_GENERIC_MANEUVER equivalent to [WKHapticType.WKHapticTypeNavigationGenericManeuver]
     * @property NAVIGATION_LEFT_TURN equivalent to [WKHapticType.WKHapticTypeNavigationLeftTurn]
     * @property NAVIGATION_RIGHT_TURN equivalent to [WKHapticType.WKHapticTypeNavigationRightTurn]
     * @property NOTIFICATION equivalent to [WKHapticType.WKHapticTypeNotification]
     * @property UNDERWATER_DEPTH_CRITICAL_PROMPT equivalent to [WKHapticType.WKHapticTypeUnderwaterDepthCriticalPrompt]
     * @property UNDERWATER_DEPTH_PROMPT equivalent to [WKHapticType.WKHapticTypeUnderwaterDepthPrompt]
     */
    public actual object DEFAULTS {

        /** Equivalent to [WKHapticType.WKHapticTypeSuccess] */
        public val SUCCESS: Vibration =
            WKHapticType.WKHapticTypeSuccess as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeFailure] */
        public val FAILURE: Vibration =
            WKHapticType.WKHapticTypeFailure as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeStart] */
        public val START: Vibration =
            WKHapticType.WKHapticTypeStart as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeStop] */
        public val STOP: Vibration =
            WKHapticType.WKHapticTypeStop as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeRetry] */
        public val RETRY: Vibration =
            WKHapticType.WKHapticTypeRetry as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeDirectionDown] */
        public val DIRECTION_DOWN: Vibration =
            WKHapticType.WKHapticTypeDirectionDown as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeDirectionUp] */
        public val DIRECTION_UP: Vibration =
            WKHapticType.WKHapticTypeDirectionUp as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeNavigationGenericManeuver] */
        public val NAVIGATION_GENERIC_MANEUVER: Vibration =
            WKHapticType.WKHapticTypeNavigationGenericManeuver as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeNavigationLeftTurn] */
        public val NAVIGATION_LEFT_TURN: Vibration =
            WKHapticType.WKHapticTypeNavigationLeftTurn as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeNavigationRightTurn] */
        public val NAVIGATION_RIGHT_TURN: Vibration =
            WKHapticType.WKHapticTypeNavigationRightTurn as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeNotification] */
        public val NOTIFICATION: Vibration =
            WKHapticType.WKHapticTypeNotification as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeUnderwaterDepthCriticalPrompt] */
        public val UNDERWATER_DEPTH_CRITICAL_PROMPT: Vibration =
            WKHapticType.WKHapticTypeUnderwaterDepthCriticalPrompt as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeUnderwaterDepthPrompt] */
        public val UNDERWATER_DEPTH_PROMPT: Vibration =
            WKHapticType.WKHapticTypeUnderwaterDepthPrompt as Vibration

        /** Equivalent to [WKHapticType.WKHapticTypeClick] */
        public actual val CLICK: Vibration =
            WKHapticType.WKHapticTypeClick as Vibration

        /** a short vibration and is shorter than [CLICK] **/
        public actual val TICK: Vibration = NOTIFICATION
    }

    /**
     * Executes a vibration immediately based on a [Vibration] pattern
     * @param pattern a platform-specific [Vibration] pattern for the vibration
     */
    public actual fun vibrate(pattern: Vibration) {
        val device = WKInterfaceDevice.currentDevice()
        device.playHaptic(pattern as WKHapticType)
    }
}