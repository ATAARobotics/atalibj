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
    public void init() {
    }

    /**
     * {@inheritDoc}
     */
    public void autonomous() {
    }

    /**
     * {@inheritDoc}
     */
    public void teleoperated() {
    }

    /**
     * {@inheritDoc}
     */
    public void disabled() {
    }

    /**
     * {@inheritDoc}
     */
    public void test() {
    }
}