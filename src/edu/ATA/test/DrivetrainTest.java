package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.ATA.module.Modules;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class DrivetrainTest extends Robot implements Modules {

    public void teleopInit() {
        drivetrain.enable();
    }

    public void teleopPeriodic() {
        drivetrain.teleop();
    }
}
