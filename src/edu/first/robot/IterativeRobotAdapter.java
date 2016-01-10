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
    @Override
    public void init() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initAutonomous() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initTeleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDisabled() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initTest() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void periodicAutonomous() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void periodicTeleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void periodicDisabled() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void periodicTest() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endAutonomous() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTeleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endDisabled() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTest() {
    }
}
