package edu.ata.driving.robot;

import edu.ata.auto.AutonomousMode;
import edu.ata.driving.modules.Module;

/**
 *
 * @author Joel Gallant
 */
public abstract class DefaultRobot extends Robot {

    private final AutonomousMode autonomousMode;
    
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
