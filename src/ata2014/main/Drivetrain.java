package ata2014.main;

import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.VictorModule;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;

/**
 * The entirety of the robot drivetrain. Includes motor controllers and the
 * abstraction of driving.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Drivetrain extends Ports {

    VictorModule frontLeftDrive = new VictorModule(FRONT_LEFT_DRIVE),
            backLeftDrive = new VictorModule(BACK_LEFT_DRIVE),
            frontRightDrive = new VictorModule(FRONT_RIGHT_DRIVE),
            backRightDrive = new VictorModule(BACK_RIGHT_DRIVE);
    edu.first.module.actuators.Drivetrain drivetrain
            = new edu.first.module.actuators.Drivetrain(frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive);
    DualActionSolenoidModule leftShifter = new DualActionSolenoidModule(LEFT_SHIFTER_IN, LEFT_SHIFTER_OUT);
    DualActionSolenoidModule rightShifter = new DualActionSolenoidModule(RIGHT_SHIFTER_IN, RIGHT_SHIFTER_OUT);

    Subsystem drivetrainSubsystem = new SubsystemBuilder()
            .add(drivetrain)
            .add(frontLeftDrive).add(backLeftDrive)
            .add(frontRightDrive).add(backRightDrive)
            .toSubsystem();
}
