package edu.ATA.main;

/**
 * Implementation of {@link Robot} that acts as an "adapter" class. Allows the
 * user to implement only the methods they need.
 *
 * @author Team 4334
 */
public abstract class DefaultRobot implements Robot {

    /**
     * Method meant to be overriden.
     *
     * @throws Error thrown when anything goes wrong
     */
    public void robotInit() throws Error {
    }

    /**
     * Method meant to be overriden.
     */
    public void autonomousInit() {
    }

    /**
     * Method meant to be overriden.
     */
    public void teleopInit() {
    }

    /**
     * Method meant to be overriden.
     */
    public void testInit() {
    }

    /**
     * Method meant to be overriden.
     *
     * @throws Error thrown when anything goes wrong
     */
    public void testPeriodic() throws Error {
    }
}
