package ata2014.subsystems;

import ata2014.main.Ports;
import edu.first.identifiers.InversedSpeedController;
import edu.first.module.Module;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.SpeedController;
import edu.first.module.actuators.SpeedControllerGroup;
import edu.first.module.actuators.VictorModule;
import edu.first.module.subsystems.Subsystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Loader extends Ports {

    VictorModule leftLoaderMotor = new VictorModule(LEFT_LOADER_MOTOR),
            rightLoaderMotor = new VictorModule(RIGHT_LOADER_MOTOR);
    SpeedControllerGroup loaderMotors = new SpeedControllerGroup(
            new SpeedController[]{new InversedSpeedController(leftLoaderMotor), rightLoaderMotor});
    DualActionSolenoidModule loaderPiston = new DualActionSolenoidModule(LOADER_PISTON_IN, LOADER_PISTON_OUT);

    Subsystem loader = new Subsystem(new Module[]{
        leftLoaderMotor, rightLoaderMotor,
        loaderPiston
    });
}
