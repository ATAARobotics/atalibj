package ata2014.subsystems;

import ata2014.controllers.ArmReset;
import ata2014.main.Ports;
import ata2014.main.Preferences;
import ata2014.modules.InversedArmPot;
import ata2014.modules.InversedVictor;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.SpeedController;
import edu.first.module.actuators.SpeedControllerGroup;
import edu.first.module.actuators.VictorModule;
import edu.first.module.controllers.PIDController;
import edu.first.module.sensors.AnalogInput;
import edu.first.module.sensors.DigitalInput;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 *
 * @author ata
 */
public interface Loader extends Ports {

    DigitalInput leftLoaderSwitch = new DigitalInput(LEFT_LOADER_SWITCH),
            rightLoaderSwitch = new DigitalInput(RIGHT_LOADER_SWITCH);
    AnalogInput leftLoaderPot = new AnalogInput(LEFT_LOADER_POT),
            rightLoaderPot = new AnalogInput(RIGHT_LOADER_POT);
    VictorModule leftLoaderMotor = new InversedVictor(LEFT_LOADER_MOTOR),
            rightLoaderMotor = new VictorModule(RIGHT_LOADER_MOTOR);
    SpeedControllerGroup loaderMotors = new SpeedControllerGroup(new SpeedController[]{leftLoaderMotor, rightLoaderMotor});
    DualActionSolenoidModule loaderPiston = new DualActionSolenoidModule(LOADER_PISTON_IN, LOADER_PISTON_OUT);

    Subsystem loader = new SubsystemBuilder()
            .add(leftLoaderSwitch).add(rightLoaderSwitch)
            .add(leftLoaderPot).add(rightLoaderPot)
            .add(leftLoaderMotor).add(rightLoaderMotor)
            .add(loaderPiston)
            .toSubsystem();

    ArmReset leftArmReset = new ArmReset(leftLoaderSwitch, leftLoaderMotor),
            rightArmReset = new ArmReset(rightLoaderSwitch, rightLoaderMotor);
    PIDController leftArmPID = new PIDController(leftLoaderPot, leftLoaderMotor,
            Preferences.getInstance().getDouble("LEFTARMP", 1),
            Preferences.getInstance().getDouble("LEFTARMI", 0),
            Preferences.getInstance().getDouble("LEFTARMD", 0));
    PIDController rightArmPID = new PIDController(rightLoaderPot, rightLoaderMotor,
            Preferences.getInstance().getDouble("RIGHTARMP", 1),
            Preferences.getInstance().getDouble("RIGHTARMI", 0),
            Preferences.getInstance().getDouble("RIGHTARMD", 0));
}
