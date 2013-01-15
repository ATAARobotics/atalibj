package edu.ATA.main;

import edu.ATA.auto.AutonomousMode;
import edu.ATA.auto.GordianScriptCache;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Utility class used to send and receive data about how the robot should behave
 * and react. Interacts with the {@link SmartDashboard} and {@link Preferences}.
 *
 * @author Joel Gallant
 */
public final class UserInfo {

    /**
     * Enum-like class that stores the keys used to access various information
     * in {@link SmartDashboard} and {@link Preferences}.
     */
    public static final class KEYS {

        private KEYS() {
        }
        /**
         * How fast packets are being transferred, and how often. Measured in
         * packets per second.
         *
         * @see TransferRateCalculator
         */
        public static final String TRANSFER_RATE = "transferRate";
        /**
         * The autonomous mode to run.
         */
        public static final String AUTONOMOUS_MODE = "autoMode";
        /**
         * A list of all autonomous modes available to choose.
         * ({@link AutonomousMode#getAutonomousModes()})
         */
        public static final String AUTONOMOUS_MODES = "autoModes";
        /**
         * The script to run if the autonomous mode is set to run it.
         */
        public static final String SCRIPT = "gordianScript";
        /**
         * The field that holds the current robot's name. ({@link Robot#name()})
         */
        public static final String ROBOT_NAME = "robotName";
    }
    private static final Preferences PREFERENCES = Preferences.getInstance();
    private static final TransferRateCalculator TRC = new TransferRateCalculator();

    // cannot be subclassed or instantiated
    private UserInfo() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Sets the value in {@link Preferences} under the key {@code key}.
     *
     * @param key key to access variable ({@link KEYS})
     * @param value value to assign to the key
     */
    public static void setPreference(String key, String value) {
        PREFERENCES.putString(key, value);
    }

    /**
     * Sets the value in {@link Preferences} under the key {@code key}.
     *
     * @param key key to access variable ({@link KEYS})
     * @param value value to assign to the key
     */
    public static void setPreference(String key, double value) {
        PREFERENCES.putDouble(key, value);
    }

    /**
     * Sets the value in {@link Preferences} under the key {@code key}.
     *
     * @param key key to access variable ({@link KEYS})
     * @param value value to assign to the key
     */
    public static void setPreference(String key, long value) {
        PREFERENCES.putLong(key, value);
    }

    /**
     * Sets the value in {@link Preferences} under the key {@code key}.
     *
     * @param key key to access variable ({@link KEYS})
     * @param value value to assign to the key
     */
    public static void setPreference(String key, int value) {
        PREFERENCES.putInt(key, value);
    }

    /**
     * Sets the value in {@link Preferences} under the key {@code key}.
     *
     * @param key key to access variable ({@link KEYS})
     * @param value value to assign to the key
     */
    public static void setPreference(String key, boolean value) {
        PREFERENCES.putBoolean(key, value);
    }

    /**
     * Saves the preferences to a file on the cRIO.
     *
     * <p> This should <b>NOT</b> be called often. Too many writes can damage
     * the cRIO's flash memory. While it is okay to save once or twice a match,
     * this should never be called every run of
     * {@link IterativeRobot#teleopPeriodic()}.
     *
     * <p> The actual writing of the file is done in a separate thread. However,
     * any call to a get or put method will wait until the table is fully saved
     * before continuing.
     *
     * <p> It's worth mentioning that the SmartDashboard has a widget to control
     * preferences that has a save button, which makes this method largely
     * unnecessary and potentially dangerous (for the reasons stated above).
     */
    public static void savePreferences() {
        PREFERENCES.save();
    }

    /**
     * Does the necessary work to take values from {@link SmartDashboard} and
     * {@link Preferences} and assign them to the correct places in the code.
     * See the source for details on implementation, as it is due to change.
     * (naturally seeing as features will be added)
     */
    public static void gatherValues() {
        AutonomousMode.setAutonomousMode(getAutonomousMode());
        GordianScriptCache.getInstance().setScript(getScript());
    }

    /**
     * Returns the amount of "packets" (as referenced in
     * {@link DriverstationInfo#getPacketCount()}) that are sent per second
     * since the last time this method was called. (or if never called before,
     * since robot started) If this method is called very frequently (ie. faster
     * than control packets are coming), it will return false / faulty results.
     * (ex. 0 / time = 0 packets per second...)
     *
     * @return packets per second
     */
    public static double getTransferRate() {
        return TRC.packetsPerMillisecond();
    }

    /**
     * Updates the value on the SmartDashboard of the transfer rate, as
     * described in {@link UserInfo#getTransferRate()}.
     */
    public static void updateTransferRate() {
        SmartDashboard.putNumber(KEYS.TRANSFER_RATE, TRC.packetsPerMillisecond());
    }

    /**
     * Returns the current autonomous mode set in {@code Preferences}. This can
     * be used to check which autonomous mode the user wants to run.
     *
     * @return the currently selected autonomous mode
     */
    public static String getAutonomousMode() {
        return PREFERENCES.getString(KEYS.AUTONOMOUS_MODE, AutonomousMode.getCurrentMode().getName());
    }

    /**
     * Updates the value in Preferences of the current autonomous mode. Uses
     * {@link AutonomousMode#getCurrentMode()} and
     * {@link AutonomousMode#getName()} to check the name of the current
     * autonomous mode set to run.
     */
    public static void updateAutonomousMode() {
        setPreference(KEYS.AUTONOMOUS_MODE, AutonomousMode.getCurrentMode().getName());
    }

    /**
     * Returns the autonomous modes listed in {@code Preferences}. This method
     * is largely unnecessary, seeing as it would have no practical uses, but is
     * here to retain the standard format for values in {@code Preferences}.
     *
     * @return string stored in preferences with all available autonomous modes
     */
    public static String getAutonomousModes() {
        return PREFERENCES.getString(KEYS.AUTONOMOUS_MODES, AutonomousMode.getAutonomousModes());
    }

    /**
     * Updates the value in Preferences of the autonomous modes selectable by
     * the user. Uses {@link AutonomousMode#getAutonomousModes()}.
     */
    public static void updateAutonomousModes() {
        setPreference(KEYS.AUTONOMOUS_MODES, AutonomousMode.getAutonomousModes());
    }

    /**
     * Returns the current script as set in {@code Preferences}. This feature is
     * still under consideration, as it is certainly likely that scripts will
     * need to be specially formatted (ie. with ";" instead of lines) in order
     * to fit the specification of preferences. This would almost eliminate any
     * usefulness given by letting the drivers edit it (very unreadable).
     *
     * @return the script stored in Preferences
     */
    public static String getScript() {
        return PREFERENCES.getString(KEYS.SCRIPT, "#no script to run");
    }

    /**
     * Updates the value in Preferences of the Gordian script inputted by the
     * user. Uses {@link GordianScriptCache#getScript()}.
     */
    public static void updateScript() {
        setPreference(KEYS.SCRIPT, GordianScriptCache.getInstance().getScript());
    }

    /**
     * Returns the current robot name as stored in {@code SmartDashboard}. If it
     * has not been set, this method just returns {@link Robot#MAIN_ROBOT}'s
     * name.
     *
     * @return name of robot stored in SmartDashboard
     */
    public static String getRobotName() {
        return SmartDashboard.getString(KEYS.ROBOT_NAME, GamePeriods.MAIN_ROBOT.name());
    }

    /**
     * Updates the value in SmartDashboard of the currently running robot's
     * name. Uses {@link Robot#MAIN_ROBOT} and {@link Robot#name()}.
     */
    public static void updateRobotName() {
        SmartDashboard.putString(KEYS.ROBOT_NAME, GamePeriods.MAIN_ROBOT.name());
    }

    private static class TransferRateCalculator {

        private int packageCountAtStart = DriverstationInfo.getPacketCount();
        private long lastPPSCheck = System.currentTimeMillis();
        private int lastPacketsCheck = 0;

        public double packetsPerMillisecond() {
            // NaN guarantee
            long s = millisecondsSinceLastCheck();
            if (s == 0) {
                return 0;
            }
            return (double) packetsSinceLastCheck() / (double) s;
        }

        public void reset() {
            lastPPSCheck = System.currentTimeMillis();
            lastPacketsCheck = 0;
        }

        private long millisecondsSinceLastCheck() {
            long s = (System.currentTimeMillis() - lastPPSCheck);
            lastPPSCheck = System.currentTimeMillis();
            return s;
        }

        private int packetsSinceLastCheck() {
            int packetCount = DriverstationInfo.getPacketCount();
            int s = packetCount - lastPacketsCheck - packageCountAtStart;
            lastPacketsCheck = packetCount - packageCountAtStart;
            return s;
        }
    }
}
