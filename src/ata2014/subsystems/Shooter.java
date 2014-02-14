package ata2014.subsystems;

import ata2014.main.Ports;
import ata2014.modules.WinchMotor;
import edu.first.module.actuators.SolenoidModule;
import edu.first.module.actuators.TalonModule;
import edu.first.module.controllers.BangBangController;
import edu.first.module.sensors.AnalogInput;
import edu.first.module.sensors.DigitalInput;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Shooter extends Ports {

    AnalogInput shooterPosition = new AnalogInput(SHOOTER_POSITION);
    DigitalInput winchLimit = new DigitalInput(WINCH_LIMIT);
    TalonModule winchMotor = new WinchMotor(winchLimit, WINCH_MOTOR);
    SolenoidModule winchRelease = new SolenoidModule(WINCH_RELEASE);

    Subsystem shooter = new SubsystemBuilder()
            .add(shooterPosition)
            .add(winchMotor)
            .add(winchRelease)
            .toSubsystem();

    BangBangController winchController = new BangBangController(shooterPosition, winchMotor);
}
