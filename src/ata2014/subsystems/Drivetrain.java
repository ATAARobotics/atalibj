package ata2014.subsystems;

import ata2014.main.Preferences;
import ata2014.controllers.DrivingPID;
import ata2014.main.Ports;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.VictorModule;
import edu.first.module.actuators.VictorModuleGroup;
import edu.first.module.sensors.EncoderModule;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 * The entirety of the robot drivetrain. Includes motor controllers and the
 * abstraction of driving.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Drivetrain extends Ports {

    double MAX_SPEED = Preferences.getInstance().getDouble("MAX_SPEED", 10000);
    double P = Preferences.getInstance().getDouble("Driving_P", 1),
            I = Preferences.getInstance().getDouble("Driving_I", 0),
            D = Preferences.getInstance().getDouble("Driving_D", 0);

    VictorModuleGroup leftDrive = new VictorModuleGroup(new VictorModule[] {
        new VictorModule(LEFT_DRIVE_1),
        new VictorModule(LEFT_DRIVE_2),
        new VictorModule(LEFT_DRIVE_3)
    });
    VictorModuleGroup rightDrive = new VictorModuleGroup(new VictorModule[] {
        new VictorModule(RIGHT_DRIVE_1),
        new VictorModule(RIGHT_DRIVE_2),
        new VictorModule(RIGHT_DRIVE_3)
    });
    edu.first.module.actuators.Drivetrain drivetrain
            = new edu.first.module.actuators.Drivetrain(leftDrive, rightDrive);
    EncoderModule leftEncoder = new EncoderModule(LEFT_ENCODER_A, LEFT_ENCODER_B, EncoderModule.InputType.RATE),
            rightEncoder = new EncoderModule(RIGHT_ENCODER_A, RIGHT_ENCODER_B, EncoderModule.InputType.RATE);
    DualActionSolenoidModule shifter = new DualActionSolenoidModule(SHIFTER_IN, SHIFTER_OUT);

    Subsystem drivetrainSubsystem = new SubsystemBuilder()
            .add(drivetrain)
            .add(leftDrive).add(rightDrive)
            .add(leftEncoder).add(rightEncoder)
            .add(shifter)
            .toSubsystem();

    DrivingPID drivingPID = new DrivingPID(drivetrain, leftEncoder, rightEncoder, P, I, D, MAX_SPEED);
}
