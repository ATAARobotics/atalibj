package edu.first.robot;

/**
 * Interface to represent something that can select a robot mode. Has no real
 * expectations.
 *
 * @author Joel Gallant
 */
public interface RobotModeSelector {

    /**
     * Returns the currently selected mode that the user desires. This method is
     * called every time the robot is disabled.
     *
     * @return current robot mode
     */
    public RobotMode getRobotMode();
}
