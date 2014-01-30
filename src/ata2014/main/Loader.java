package ata2014.main;

import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.SpeedController;
import edu.first.module.actuators.SpeedControllerGroup;
import edu.first.module.actuators.VictorModule;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author ata
 */
public interface Loader extends Ports {

    VictorModule leftLoaderMotor = new VictorModule(LEFT_LOADER_MOTOR),
            rightLoaderMotor = new VictorModule(RIGHT_LOADER_MOTOR);
    SpeedControllerGroup loaderMotors = new SpeedControllerGroup(new SpeedController[] {leftLoaderMotor, rightLoaderMotor});
    DualActionSolenoidModule leftLoaderPiston = new DualActionSolenoidModule(LEFT_LOADER_PISTON_IN, LEFT_LOADER_PISTON_OUT),
            rightLoaderPiston = new DualActionSolenoidModule(RIGHT_LOADER_PISTON_IN, RIGHT_LOADER_PISTON_OUT);

    Subsystem loader = new SubsystemBuilder()
            .add(leftLoaderMotor).add(rightLoaderMotor)
            .add(leftLoaderPiston).add(rightLoaderPiston)
            .toSubsystem();
}
