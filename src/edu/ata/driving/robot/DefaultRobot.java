package edu.ata.driving.robot;

import edu.ata.auto.AutonomousMode;
import edu.ata.driving.modules.Module;

/**
 * {@link Robot} object that uses {@link AutonomousMode} for autonomous and
 * implements {@code init()} and {@code disabled()} (Does nothing within).
 *
 * @author Joel Gallant
 */
public abstract class DefaultRobot extends Robot {

    private final AutonomousMode autonomousMode;

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

    // Overide this to use
    public void init() {
    }

    // Overide this to use
    public void disabled() {
    }

    public void autonomous() {
        autonomousMode.run();
    }
}
