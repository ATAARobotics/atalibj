package ata2014.subsystems;

import ata2014.settings.ConstantSettings;
import edu.first.module.joysticks.XboxController;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Joysticks extends ConstantSettings {

    XboxController joystick1 = new XboxController(1, JOYSTICK_DEADBAND),
            joystick2 = new XboxController(2, JOYSTICK_DEADBAND);

    Subsystem joysticks = new SubsystemBuilder()
            .add(joystick1).add(joystick2)
            .toSubsystem();
}
