package ata2014.main;

import edu.first.module.actuators.VictorModule;

/**
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
}
