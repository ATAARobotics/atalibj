package edu.first.robot;

/**
 * An adapter pattern class for robot modes that use the {@link SimpleRobot}
 * class, but do not need to use all of its methods. Basically, if a robot mode
 * does not use all of its methods, it should extend this class instead to avoid
 * clutter.
 *
 * @since May 08 13
 * @author Joel Gallant
 */
public class SimpleRobotAdapter extends SimpleRobot {

    /**
     * Constructs the robot mode using the name to show the user. The name
     * should be recognizable, like "Defensive Mode" or "Single Driver Mode".
     *
     * @param name easily understandable phrase to refer to the mode as
     */
    public SimpleRobotAdapter(String name) {
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
    public void autonomous() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disabled() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void test() {
    }
}
