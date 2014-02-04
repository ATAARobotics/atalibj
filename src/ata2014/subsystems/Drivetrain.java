package ata2014.subsystems;

import ata2014.main.Preferences;
import ata2014.controllers.DrivingPID;
import ata2014.main.Ports;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.VictorModule;
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

    VictorModule frontLeftDrive = new VictorModule(FRONT_LEFT_DRIVE),
            backLeftDrive = new VictorModule(BACK_LEFT_DRIVE),
            frontRightDrive = new VictorModule(FRONT_RIGHT_DRIVE),
            backRightDrive = new VictorModule(BACK_RIGHT_DRIVE);
    edu.first.module.actuators.Drivetrain drivetrain
            = new edu.first.module.actuators.Drivetrain(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
    EncoderModule leftEncoder = new EncoderModule(LEFT_ENCODER_A, LEFT_ENCODER_B, EncoderModule.InputType.RATE),
            rightEncoder = new EncoderModule(RIGHT_ENCODER_A, RIGHT_ENCODER_B, EncoderModule.InputType.RATE);
    DualActionSolenoidModule shifter = new DualActionSolenoidModule(SHIFTER_IN, SHIFTER_OUT);

    Subsystem drivetrainSubsystem = new SubsystemBuilder()
            .add(drivetrain)
            .add(frontLeftDrive).add(backLeftDrive)
            .add(frontRightDrive).add(backRightDrive)
            .add(leftEncoder).add(rightEncoder)
            .add(shifter)
            .toSubsystem();

    DrivingPID drivingPID = new DrivingPID(drivetrain, leftEncoder, rightEncoder, P, I, D, MAX_SPEED);
}
