package ata2014.subsystems;

import ata2014.controllers.DrivingPID;
import ata2014.settings.Ports;
import ata2014.settings.Settings;
import edu.first.identifiers.Function;
import edu.first.identifiers.InversedSpeedController;
import edu.first.identifiers.Output;
import edu.first.identifiers.TransformedInput;
import edu.first.module.Module;
import edu.first.module.actuators.Drivetrain;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.VictorModule;
import edu.first.module.actuators.VictorModuleGroup;
import edu.first.module.controllers.PIDController;
import edu.first.module.sensors.EncoderModule;
import edu.first.module.subsystems.Subsystem;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Drive extends Ports, Settings {

    VictorModuleGroup leftDrive = new VictorModuleGroup(new VictorModule[]{
        new VictorModule(LEFT_DRIVE_1),
        new VictorModule(LEFT_DRIVE_2),
        new VictorModule(LEFT_DRIVE_3)
    });
    VictorModuleGroup rightDrive = new VictorModuleGroup(new VictorModule[]{
        new VictorModule(RIGHT_DRIVE_1),
        new VictorModule(RIGHT_DRIVE_2),
        new VictorModule(RIGHT_DRIVE_3)
    });
    Drivetrain drivetrain = new Drivetrain(new InversedSpeedController(leftDrive), rightDrive);
    EncoderModule leftDriveEncoder = new EncoderModule(LEFT_DRIVE_ENCODER_A, LEFT_DRIVE_ENCODER_B, EncoderModule.InputType.RATE);
    EncoderModule rightDriveEncoder = new EncoderModule(RIGHT_DRIVE_ENCODER_A, RIGHT_DRIVE_ENCODER_B, EncoderModule.InputType.RATE);

    Subsystem drive = new Subsystem(new Module[]{
        drivetrain,
        leftDrive, rightDrive,
        leftDriveEncoder, rightDriveEncoder
    });

    DualActionSolenoidModule shifters = new DualActionSolenoidModule(SHIFTER_IN, SHIFTER_OUT);
    DrivingPID drivingPID = new DrivingPID(drivetrain, leftDriveEncoder, rightDriveEncoder,
            DRIVING_P.get(), DRIVING_I.get(), DRIVING_D.get(), DRIVING_MAX_SPEED.get());
}
