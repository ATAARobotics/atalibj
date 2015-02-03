package edu.first.util;

import java.io.BufferedInputStream;
import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Contains all DriverStation static methods and other useful methods used for
 * finding the status of the robot / DriverStation.
 *
 * <p>
 * <i>Please note:</i> <b>This class is not the same as {@link DriverStation}.
 * That is the direct link to the actual DriverStation. This is a convenience
 * utility class with static methods referencing that class.</b>
 *
 * @since May 14 13
 * @author Joel Gallant
 */
public final class DriverstationInfo {

    private static final DriverStation DS = DriverStation.getInstance();

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
     * Gets the location of the team's driver station controls.
     *
     * @return the location of the team's driver station controls: 1, 2, or 3
     */
    public static int getAllianceLocation() {
        return DS.getLocation();
    }

    /**
     * The Alliance name.
     *
     * <p>
     * <i>"Red"</i>, <i>"Blue"</i> or <i>"Invalid"</i>.
     *
     * @return Alliance name
     */
    public static String getAllianceName() {
        return DS.getAlliance().name();
    }

    /**
     * Return the team number that the Driver Station is configured for.
     *
     * @return the team number
     */
    public static int getTeamNumber() {
        Runtime run = Runtime.getRuntime();
        Process proc;
        try {
            proc = run.exec("hostname");
            BufferedInputStream in = new BufferedInputStream(
                    proc.getInputStream());
            byte[] b = new byte[256];
            in.read(b, 0, 256);
            return Integer.parseInt(new String(b).trim());
        } catch (IOException e1) {
            return 0000;
        }
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
     * <p>
     * This method assumes that the battery voltage is being measured through
     * the voltage divider on an analog breakout.
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
     * <p>
     * <b> USERMESSAGE: This is not an official time (so it cannot be used to
     * argue with referees) </b>
     *
     * @return match time in seconds since the beginning of autonomous
     */
    public static double getMatchTime() {
        return DS.getMatchTime();
    }

    /**
     * Returns the game mode that the robot is in.
     *
     * @return which game mode it is
     */
    public static GameMode getGamePeriod() {
        return DS.isEnabled() ? (DS.isOperatorControl() ? GameMode.TELEOPERATED
                : (DS.isAutonomous() ? GameMode.AUTONOMOUS : GameMode.DISABLED))
                : GameMode.DISABLED;
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
     * Returns whether or not the DriverStation is currently in teleoperated
     * mode.
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
     * Enum that represents the different game modes a robot can be in.
     */
    public static enum GameMode {

        AUTONOMOUS, TELEOPERATED, DISABLED;
    }
}
