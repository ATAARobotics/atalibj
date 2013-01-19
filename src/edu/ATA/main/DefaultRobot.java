package edu.ATA.main;

/**
 * Implementation of {@link Robot} that acts as an "adapter" class. Allows the
 * user to implement only the methods they need. The methods that are not
 * implemented do nothing. This class is meant to keep code short.
 *
 * @author Team 4334
 */
public abstract class DefaultRobot implements Robot {

    /**
     * Method meant to be overriden that runs once at the start of the match.
     *
     * @throws Error thrown when anything goes wrong
     */
    public void robotInit() throws Error {
    }

    /**
     * Method meant to be overriden that runs once before autonomous mode.
     */
    public void autonomousInit() {
    }

    /**
     * Method meant to be overriden that runs periodically during autonomous
     * mode.
     *
     * @throws Error thrown when anything goes wrong
     */
    public void autonomousPeriodic() throws Error {
    }

    /**
     * Method meant to be overriden that runs once before teleoperated mode.
     */
    public void teleopInit() {
    }

    /**
     * Method meant to be overriden that runs periodically during teleoperated
     * mode.
     *
     * @throws Error thrown when anything goes wrong
     */
    public void teleopPeriodic() throws Error {
    }

    /**
     * Method meant to be overriden that runs once before test mode.
     */
    public void testInit() {
    }

    /**
     * Method meant to be overriden that runs periodically during test mode.
     *
     * @throws Error thrown when anything goes wrong
     */
    public void testPeriodic() throws Error {
    }

    /**
     * Method meant to be overriden that runs once before disabled mode.
     * @throws Error thrown when anything goes wrong
     */
    public void disabled() throws Error {
    }
}
