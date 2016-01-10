package edu.first.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Main class representing an "iterative" style robot mode. This is the same
 * idea as {@link edu.wpi.first.wpilibj.IterativeRobot}, but without any of the
 * {@link RobotBase} parts attached. The basic idea is that there are methods
 * called 50 times per second for each game mode, each doing different things in
 * different modes.
 *
 * This class is meant to be extended, to make its {@code RobotMode} methods
 * visible.
 *
 * @since May 07 13
 * @author Joel Gallant
 */
public abstract class IterativeRobot implements RobotMode {

    // Name for displaying on RobotModeSelector and other things (logging, etc.)
    private final String name;

    /**
     * Constructs the robot mode using the name to show the user. The name
     * should be recognizable, like "Defensive Mode" or "Single Driver Mode".
     *
     * @param name easily understandable phrase to refer to the mode as
     */
    public IterativeRobot(String name) {
        this.name = name;
    }

    /**
     * Returns the general name to refer to the robot mode as.
     *
     * @return easily understandable phrase to refer to the mode as
     */
    @Override
    public final String getName() {
        return name;
    }

    /**
     * Returns the name of the robot mode.
     *
     * @see IterativeRobot#getName()
     * @return easily understandable phrase to refer to the mode as
     */
    @Override
    public final String toString() {
        return getName();
    }
}
