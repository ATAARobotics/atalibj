package edu.first.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 * Contains all DriverStation static methods and other useful methods used for
 * finding the status of the robot / DriverStation.
 *
 * <p> <i>Please note:</i> <b>This class is not the same as
 * {@link DriverStation}. That is the direct link to the actual DriverStation.
 * This is a convenience utility class with static methods referencing that
 * class.</b>
 *
 * @since May 14 13
 * @author Joel Gallant
 */
public final class DriverstationInfo {

    private static final DriverStation DS = DriverStation.getInstance();
    private static final DriverStationEnhancedIO IO = DS.getEnhancedIO();
    /**
     * Disabled mode. Used in {@link DriverstationInfo#getGamePeriod()}.
     */
    public static final String DISABLED = "disabled",
            /**
             * Teleoperated mode. Used in
             * {@link DriverstationInfo#getGamePeriod()}.
             */
            TELEOP = "teleop",
            /**
             * Autonomous mode. Used in
             * {@link DriverstationInfo#getGamePeriod()}.
             */
            AUTONOMOUS = "autonomous";

    // cannot be subclassed or instantiated
    private DriverstationInfo() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Returns the singleton instance of the Driverstation.
     *
     * @return driverstation with all features
     */
    public static DriverStation getDS() {
        return DS;
    }

    /**
     * Get the interface to the enhanced IO of the driver station.
     *
     * @return an enhanced IO object for the advanced features of the driver
     * station
     */
    public static DriverStationEnhancedIO getIO() {
        return IO;
    }

    /**
     * Gets the location of the team's driver station controls.
     *
     * @return the location of the team's driver station controls: 1, 2, or 3
     */
    public static int getAllianceLocation() {
        return DS.getLocation();
    }

    /**
     * Return the team number that the Driver Station is configured for.
     *
     * @return the team number
     */
    public static int getTeamNumber() {
        return DS.getTeamNumber();
    }

    /**
     * Return the DS packet number. The packet number is the index of this set
     * of data returned by the driver station. Each time new data is received,
     * the packet number (included with the sent data) is returned.
     *
     * @return the DS packet number.
     */
    public static int getPacketCount() {
        return DS.getPacketNumber();
    }

    /**
     * Is the driver station attached to a Field Management System? Note: This
     * does not work with the Blue DS.
     *
     * @return if the robot is competing on a field being controlled by a Field
     * Management System
     */
    public static boolean FMSattached() {
        return DS.isFMSAttached();
    }

    /**
     * Read the battery voltage from the specified AnalogChannel.
     *
     * <p> This method assumes that the battery voltage is being measured
     * through the voltage divider on an analog breakout.
     *
     * @return the battery voltage.
     */
    public static double getBatteryVoltage() {
        return DS.getBatteryVoltage();
    }

    /**
     * Return the approximate match time. The FMS does not currently send the
     * official match time to the robots. This returns the time since the enable
     * signal sent from the Driver Station. At the beginning of autonomous, the
     * time is reset to 0.0 seconds. At the beginning of teleoperated, the time
     * is reset to +15.0 seconds. If the robot is disabled, this returns 0.0
     * seconds.
     *
     * <p> <b> USERMESSAGE: This is not an official time (so it cannot be used
     * to argue with referees) </b>
     *
     * @return match time in seconds since the beginning of autonomous
     */
    public static double getMatchTime() {
        return DS.getMatchTime();
    }

    /**
     * Gets a string value of which game mode robot should be in.
     *
     * @return {@link DriverstationInfo#TELEOP},
     * {@link DriverstationInfo#AUTONOMOUS} or
     * {@link DriverstationInfo#DISABLED}.
     */
    public static String getGamePeriod() {
        return DS.isEnabled() ? (DS.isOperatorControl() ? TELEOP
                : (DS.isAutonomous() ? AUTONOMOUS : DISABLED)) : DISABLED;
    }

    /**
     * Returns whether or not the DriverStation is currently in autonomous mode.
     *
     * @return if autonomous
     */
    public static boolean isAutonomous() {
        return DS.isAutonomous();
    }

    /**
     * Returns whether or not the DriverStation is currently in teleoperated mode.
     *
     * @return if teleoperated
     */
    public static boolean isTeleoperated() {
        return DS.isOperatorControl();
    }

    /**
     * Returns whether or not the DriverStation is currently in test mode.
     *
     * @return if test
     */
    public static boolean isTest() {
        return DS.isTest();
    }

    /**
     * Gets a value indicating whether the Driver Station requires the robot to
     * be enabled.
     *
     * @return if the robot is enabled
     */
    public static boolean isEnabled() {
        return DS.isEnabled();
    }

    /**
     * The Alliance name.
     *
     * <p> <i>"Red"</i>, <i>"Blue"</i> or <i>"invalid"</i>.
     *
     * @return Alliance name
     */
    public static String getAllianceName() {
        return DS.getAlliance().name;
    }
}