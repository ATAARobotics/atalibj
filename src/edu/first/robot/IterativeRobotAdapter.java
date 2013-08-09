package edu.first.robot;

/**
 * An adapter pattern class for robot modes that are iterative, but do not use
 * all methods in the {@link IterativeRobot} class. Basically, if a robot mode
 * does not use all iterative methods, it should extend this class instead to
 * avoid clutter.
 *
 * @since May 07 13
 * @author Joel Gallant
 */
public class IterativeRobotAdapter extends IterativeRobot {

    /**
     * Constructs the robot mode using the name to show the user. The name
     * should be recognizable, like "Defensive Mode" or "Single Driver Mode".
     *
     * @param name easily understandable phrase to refer to the mode as
     */
    public IterativeRobotAdapter(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * {@inheritDoc}
     */
    public void initAutonomous() {
    }

    /**
     * {@inheritDoc}
     */
    public void initTeleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    public void initDisabled() {
    }

    /**
     * {@inheritDoc}
     */
    public void initTest() {
    }

    /**
     * {@inheritDoc}
     */
    public void periodicAutonomous() {
    }

    /**
     * {@inheritDoc}
     */
    public void periodicTeleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    public void periodicDisabled() {
    }

    /**
     * {@inheritDoc}
     */
    public void periodicTest() {
    }

    /**
     * {@inheritDoc}
     */
    public void endAutonomous() {
    }

    /**
     * {@inheritDoc}
     */
    public void endTeleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    public void endDisabled() {
    }

    /**
     * {@inheritDoc}
     */
    public void endTest() {
    }
}