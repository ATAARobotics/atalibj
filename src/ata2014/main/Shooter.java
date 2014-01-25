package ata2014.main;

import edu.first.module.actuators.SolenoidModule;
import edu.first.module.actuators.TalonModule;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Shooter extends Ports {

    TalonModule winchMotor = new TalonModule(WINCH_MOTOR);
    SolenoidModule winchRelease = new SolenoidModule(WINCH_RELEASE);
    
    Subsystem shooter = new SubsystemBuilder()
            .add(winchMotor)
            .add(winchRelease)
            .toSubsystem();
}
