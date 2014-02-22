package ata2014.subsystems;

import ata2014.controllers.WinchBack;
import ata2014.main.Ports;
import ata2014.modules.WinchMotor;
import edu.first.module.Module;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.controllers.BangBangController;
import edu.first.module.sensors.AnalogInput;
import edu.first.module.sensors.DigitalInput;
import edu.first.module.subsystems.Subsystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Winch extends Ports {

    DigitalInput winchLimit = new DigitalInput(WINCH_LIMIT);
    WinchMotor winchMotor = new WinchMotor(winchLimit, WINCH_MOTOR);
    DualActionSolenoidModule winchRelease = new DualActionSolenoidModule(WINCH_RELEASE_IN, WINCH_RELEASE_OUT);

    Subsystem winch = new Subsystem(new Module[]{
        winchLimit, winchMotor, winchRelease
    });

    AnalogInput winchPosition = new AnalogInput(WINCH_POSITION);
    WinchBack winchBack = new WinchBack(winchMotor);
    BangBangController winchController = new BangBangController(winchPosition, winchMotor);
}
