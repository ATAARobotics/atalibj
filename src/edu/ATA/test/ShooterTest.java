package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.ATA.module.Modules;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ShooterTest extends Robot implements Modules {

    public void teleopInit() {
        shooter.enable();
    }

    public void teleopPeriodic() {
        shooter.teleop();
    }
}
