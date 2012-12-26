package edu.ata.driving.robot;

import edu.ata.auto.AutonomousMode;
import edu.ata.auto.AutonomousSelector;
import edu.ata.driving.modules.Module;

/**
 * {@link Robot} object that uses {@link AutonomousMode} for autonomous and
 * implements {@code init()} and {@code disabled()} (Does nothing within).
 *
 * @author Joel Gallant
 */
public abstract class DefaultRobot extends Robot {

    private AutonomousMode autonomousMode;
    private AutonomousSelector autonomousSelector;

    /**
     * Constructs the robot with its modules, a name and autonomous mode.
     *
     * @param modules modules used in the robot
     * @param name name of the robot
     * @param autonomousMode autonomous mode to be used
     */
    public DefaultRobot(Module[] modules, String name, AutonomousMode autonomousMode) {
        super(modules, name);
        this.autonomousMode = autonomousMode;
    }

    /**
     * Constructs the robot with its modules, a name and an
     * {@link AutonomousSelector} to automatically select the autonomous mode.
     *
     * @param modules modules used in the robot
     * @param name name of the robot
     * @param autonomousSelector auto mode selector
     */
    public DefaultRobot(Module[] modules, String name, AutonomousSelector autonomousSelector) {
        super(modules, name);
        this.autonomousSelector = autonomousSelector;
    }

    /**
     * Constructs the robot with its modules and a name. Autonomous mode is not
     * set.
     *
     * @param modules modules used in the robot
     * @param name name of the robot
     */
    public DefaultRobot(Module[] modules, String name) {
        super(modules, name);
    }

    // Overide this to use
    public void init() {
    }

    // Overide this to use
    public void disabled() {
    }

    /**
     * Runs the selected autonomous mode. If the robot was built with an
     * {@link AutonomousSelector}, it is used to select the appropriate
     * autonomous mode.
     */
    public final void autonomous() {
        if (autonomousSelector != null) {
            try {
                autonomousMode = autonomousSelector.get();
            } catch (AutonomousSelector.AutonomousModeNotSetException ex) {
            }
        }
        if (autonomousMode != null) {
            autonomousMode.run();
        }
    }

    /**
     * Sets the autonomous mode to run.
     *
     * @param autonomousMode mode to run
     */
    public final void setAutonomous(AutonomousMode autonomousMode) {
        this.autonomousMode = autonomousMode;
    }
}
