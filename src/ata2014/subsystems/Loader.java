package ata2014.subsystems;

import ata2014.settings.Ports;
import ata2014.settings.Settings;
import edu.first.identifiers.Function;
import edu.first.identifiers.InversedSpeedController;
import edu.first.identifiers.TransformedOutput;
import edu.first.module.Module;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.SpeedController;
import edu.first.module.actuators.SpeedControllerGroup;
import edu.first.module.actuators.VictorModule;
import edu.first.module.controllers.PIDController;
import edu.first.module.sensors.AnalogInput;
import edu.first.module.subsystems.Subsystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Loader extends Ports, Settings {

    VictorModule leftLoaderMotor = new VictorModule(LEFT_LOADER_MOTOR),
            rightLoaderMotor = new VictorModule(RIGHT_LOADER_MOTOR);
    SpeedControllerGroup loaderMotors = new SpeedControllerGroup(
            new SpeedController[]{new InversedSpeedController(leftLoaderMotor), rightLoaderMotor});
    DualActionSolenoidModule loaderPiston = new DualActionSolenoidModule(LOADER_PISTON_IN, LOADER_PISTON_OUT);
    AnalogInput loaderPosition = new AnalogInput(LOADER_POSITION);

    Subsystem loader = new Subsystem(new Module[]{
        leftLoaderMotor, rightLoaderMotor,
        loaderPiston,
        loaderPosition
    });

    PIDController loaderController = new PIDController(loaderPosition,
            new TransformedOutput(loaderMotors, new Function.ProductFunction(LOADER_MAX_SPEED)),
            LOADER_P.get(), LOADER_I.get(), LOADER_D.get());
}
