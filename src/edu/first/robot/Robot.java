package edu.first.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * General interface for functional robots. Is an abstraction to
 * {@link IterativeRobot} to provide a way to change the way the robot works. An
 * easy way to to this is {@link GamePeriods#setRobot(edu.first.main.Robot)}.
 *
 * @author Joel Gallant
 */
public interface Robot {

    /**
     * Run once when robot starts.
     */
    public void robotInit();

    /**
     * Runs once before disabled mode.
     */
    public void disabledInit();

    /**
     * Runs periodically (20ms) during disabled mode.
     */
    public void disabledPeriodic();

    /**
     * Runs once before autonomous mode.
     */
    public void autonomousInit();

    /**
     * Runs periodically (20ms) during autonomous mode.
     */
    public void autonomousPeriodic();

    /**
     * Runs once before teleoperated mode.
     */
    public void teleopInit();

    /**
     * Runs periodically (20ms) during teleoperated mode.
     */
    public void teleopPeriodic();

    /**
     * Runs once before test mode.
     */
    public void testInit();

    /**
     * Runs periodically (20ms) during test mode.
     */
    public void testPeriodic();
}
