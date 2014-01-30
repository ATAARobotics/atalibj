package ata2014.main;

import edu.first.module.joysticks.XboxController;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 * Joysticks used for controlling the robot.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Joysticks {

    double DEADBAND = 0.17;
    
    XboxController joystick1 = new XboxController(1, DEADBAND),
            joystick2 = new XboxController(2, DEADBAND);

    Subsystem joysticks = new SubsystemBuilder()
            .add(joystick1).add(joystick2)
            .toSubsystem();
}
