package edu.first.robot;

import edu.first.main.Constants;
import edu.first.main.GamePeriods;

/**
 * The framework for all "modes" a robot can perform, this is the basic class
 * that tells the robot what it should do and when it should do it.
 * {@code RobotMode} can be used in the same way as
 * {@link edu.wpi.first.wpilibj.IterativeRobot}, making the robot only capable
 * of acting in one way, or it can be used as a function of the robot.
 * {@code RobotMode} is a robust way to make it possible to extend and test with
 * different kinds of code structures and techniques without having to settle on
 * one until it works.
 *
 * Good examples of robot modes are:
 * <ul>
 * <li> Testing
 * <li> Competition Mode
 * <li> FeatureXYZ Test
 * <li> Single Driver Mode
 * <li> Simple Drive Mode
 * <li> Pre-Programmed Autonomous
 * <li> Script Autonomous
 * </ul>
 *
 * Bad examples of robot modes are:
 * <ul>
 * <li> Tank drive
 * <li> Different Ports
 * <li> Different autonomous
 * </ul>
 *
 * Notice that a good robot mode gives the user control over some functionality
 * that is a fundamental change in how it works. Bad robot modes change small
 * things that could, and should be changed by things that are not robot modes.
 * Even though the {@code RobotMode} pattern could let you do things like that
 * relatively easily, generally there are much better ways to do so.
 *
 * @since May 07 13
 * @see IterativeRobot
 * @see SimpleRobot
 * @author Joel Gallant
 */
public interface RobotMode extends Constants {

    /**
     * Initializes the robot. This means that after this method is run,
     * everything inside of it has the potential to perform actions.
     *
     * Run in {@link GamePeriods#robotInit()}.
     */
    public void init();

    /**
     * Initializes autonomous mode. This method should ensure that autonomous
     * mode is ready to run, or is already running.
     *
     * Run in {@link GamePeriods#autonomousInit()}.
     */
    public void initAutonomous();

    /**
     * Initializes teleoperated mode. This method should ensure that
     * teleoperated mode is ready too run, or is already running.
     *
     * Run in {@link GamePeriods#teleopInit()}.
     */
    public void initTeleoperated();

    /**
     * Initializes disabled mode. This method should ensure that disabled mode
     * is ready too run, or is already running.
     *
     * Run in {@link GamePeriods#disabledInit()}.
     */
    public void initDisabled();

    /**
     * Initializes test mode. This method should ensure that test mode is ready
     * too run, or is already running.
     *
     * Run in {@link GamePeriods#testInit()}.
     */
    public void initTest();

    /**
     * Runs periodically during the autonomous period. The standard for periodic
     * is 50Hz, or 20ms. This standard is not guaranteed.
     */
    public void periodicAutonomous();

    /**
     * Runs periodically during the teleoperated period. The standard for
     * periodic is 50Hz, or 20ms. This standard is not guaranteed.
     */
    public void periodicTeleoperated();

    /**
     * Runs periodically during the disabled period. The standard for periodic
     * is 50Hz, or 20ms. This standard is not guaranteed.
     */
    public void periodicDisabled();

    /**
     * Runs periodically during the test period. The standard for periodic is
     * 50Hz, or 20ms. This standard is not guaranteed.
     */
    public void periodicTest();

    /**
     * Run once after autonomous mode is done running.
     */
    public void endAutonomous();

    /**
     * Run once after teleoperated mode is done running.
     */
    public void endTeleoperated();

    /**
     * Run once after disabled mode is done running.
     */
    public void endDisabled();

    /**
     * Run once after test mode is done running.
     */
    public void endTest();

    /**
     * Returns the name of the {@code RobotMode} to show the user when
     * necessary.
     *
     * @return user recognizable name
     */
    public String getName();
}
