package edu.first.robot;

import edu.first.main.GamePeriods;
import edu.first.util.DriverstationInfo;

/**
 * Main class representing a "Simple" style robot mode. This is basically the
 * equivalent to {@link edu.wpi.first.wpilibj.SimpleRobot}, but as a
 * {@link RobotMode}. The basic framework is that each mode has its own method,
 * which is called once every time the mode starts.
 *
 * @since May 08 13
 * @author Joel Gallant
 */
public abstract class SimpleRobot implements RobotMode {

    // Name for displaying on RobotModeSelector and other things (logging, etc.)
    private final String name;

    /**
     * Constructs the robot mode using the name to show the user. The name
     * should be recognizable, like "Defensive Mode" or "Single Driver Mode".
     *
     * @param name easily understandable phrase to refer to the mode as
     */
    public SimpleRobot(String name) {
        this.name = name;
    }

    /**
     * Runs once when the robot is starting. Follows the same timing as
     * {@link GamePeriods#robotInit()}.
     */
    @Override
    public abstract void init();

    /**
     * Runs once every time autonomous mode is started.
     */
    public abstract void autonomous();

    /**
     * Runs once every time teleoperated mode is started.
     */
    public abstract void teleoperated();

    /**
     * Runs once every time robot is disabled.
     */
    public abstract void disabled();

    /**
     * Runs once every time test mode is started.
     */
    public abstract void test();

    /**
     * Returns whether the robot is currently enabled.
     *
     * @return if robot is enabled
     */
    protected final boolean isEnabled() {
        return DriverstationInfo.isEnabled();
    }

    /**
     * Returns whether the robot is currently enabled in autonomous mode.
     *
     * @return if robot is in autonomous
     */
    protected final boolean isAutonomous() {
        return DriverstationInfo.isAutonomous();
    }

    /**
     * Returns whether the robot is currently enabled in teleoperated mode.
     *
     * @return if robot is in teleoperated
     */
    protected final boolean isTeleoperated() {
        return DriverstationInfo.isTeleoperated();
    }

    /**
     * Returns whether the robot is currently enabled in test mode.
     *
     * @return if robot is in test mode
     */
    protected final boolean isTest() {
        return DriverstationInfo.isTest();
    }

    /**
     * Calls {@link SimpleRobot#autonomous()}.
     */
    @Override
    public final void initAutonomous() {
        autonomous();
    }

    /**
     * Calls {@link SimpleRobot#teleoperated()}.
     */
    @Override
    public final void initTeleoperated() {
        teleoperated();
    }

    /**
     * Calls {@link SimpleRobot#disabled()}.
     */
    @Override
    public final void initDisabled() {
        disabled();
    }

    /**
     * Calls {@link SimpleRobot#test()}.
     */
    @Override
    public final void initTest() {
        test();
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void periodicAutonomous() {
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void periodicTeleoperated() {
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void periodicDisabled() {
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void periodicTest() {
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void endAutonomous() {
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void endTeleoperated() {
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void endDisabled() {
    }

    /**
     * @deprecated not used in {@code SimpleRobot}
     */
    @Override
    public final void endTest() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the {@code RobotMode}'s name.
     *
     * @see #getName()
     * @return easily understandable phrase to refer to the mode as
     */
    @Override
    public String toString() {
        return getName();
    }
}
