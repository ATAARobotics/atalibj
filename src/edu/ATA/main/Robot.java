package edu.ATA.main;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * Interface meant to represent a class that can be used as the main 'function'
 * of the robot. In realistic terms, classes that are {@link Robot "Robots"} are
 * ways for the robot to behave in a game. It is possible to change the
 * currently running robot in {@link GamePeriods} by changing
 * {@link GamePeriods#robot}.
 *
 * <p> This interface has no real expectations of its implementation, but it is
 * important to be aware of how {@link GamePeriods} works, as well as
 * {@link IterativeRobot}. Both determine how implementations of this interface
 * will behave in real world situations. Understanding how to effectively make
 * implementations work in tandem with lower level layers of the WPI libraries
 * is critical to avoid performance / use critical mistakes.
 *
 * @see IterativeRobot
 * @see GamePeriods
 * @author Joel Gallant
 */
public interface Robot {

    /**
     * Returns the name of the robot. This method is called only for for user
     * displays / notifications. There are no presumptions or expectations
     * involved with the results of this method, but it might be helpful to
     * distinguish different robots with different names, to help with debugging
     * and driver understanding.
     *
     * @return the 'name' of the robot implementation
     */
    String name();

    /**
     * This function is run when the robot is first started up and is used for
     * any initialization code.
     *
     * <p> <i> Note: Please convert all {@link Throwable throwables} into
     * {@link Error} (or subclass) format, to ensure errors do not go unchecked
     * and break low level API workings.</i>
     *
     * @see IterativeRobot#robotInit()
     * @throws Error thrown when anything goes wrong
     */
    void robotInit() throws Error;

    /**
     * The function is called once at the start of autonomous mode.
     *
     * @see IterativeRobot#autonomousInit()
     */
    void autonomousInit();

    /**
     * This function is called periodically during autonomous.
     *
     * <p> <i> Note: Please convert all {@link Throwable throwables} into
     * {@link Error} (or subclass) format, to ensure errors do not go unchecked
     * and break low level API workings.</i>
     *
     * @see IterativeRobot#autonomousPeriodic()
     * @throws Error thrown when anything goes wrong
     */
    void autonomousPeriodic() throws Error;

    /**
     * This function is called once at the start of teleoperated mode.
     *
     * @see IterativeRobot#teleopInit()
     */
    void teleopInit();

    /**
     * This function is called periodically during operator control.
     *
     * <p> <i> Note: Please convert all {@link Throwable throwables} into
     * {@link Error} (or subclass) format, to ensure errors do not go unchecked
     * and break low level API workings.</i>
     *
     * @see IterativeRobot#teleopPeriodic()
     * @throws Error thrown when anything goes wrong
     */
    void teleopPeriodic() throws Error;

    /**
     * This function is called once at the start of test mode.
     *
     * @see IterativeRobot#testInit()
     */
    void testInit();

    /**
     * This function is called periodically in test mode.
     *
     * <p> <i> Note: Please convert all {@link Throwable throwables} into
     * {@link Error} (or subclass) format, to ensure errors do not go unchecked
     * and break low level API workings.</i>
     *
     * @see IterativeRobot#testPeriodic()
     * @throws Error thrown when anything goes wrong
     */
    void testPeriodic() throws Error;

    /**
     * This function is called once at the start of disabled mode.
     *
     * @see IterativeRobot#disabledInit()
     * @throws Error thrown when anything goes wrong
     */
    void disabled() throws Error;
}
