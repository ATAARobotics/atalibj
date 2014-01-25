package ata2014.main;

import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.VictorModule;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author ata
 */
public interface Loader extends Ports {

    VictorModule leftLoaderMotor = new VictorModule(LEFT_LOADER_MOTOR),
            rightLoaderSide = new VictorModule(RIGHT_LOADER_MOTOR);
    DualActionSolenoidModule leftLoaderPiston = new DualActionSolenoidModule(LEFT_LOADER_PISTON_IN, LEFT_LOADER_PISTON_OUT),
            rightLoaderPiston = new DualActionSolenoidModule(RIGHT_LOADER_PISTON_IN, RIGHT_LOADER_PISTON_OUT);

    Subsystem loader = new SubsystemBuilder()
            .add(leftLoaderMotor).add(rightLoaderSide)
            .add(leftLoaderPiston).add(rightLoaderPiston)
            .toSubsystem();
}
