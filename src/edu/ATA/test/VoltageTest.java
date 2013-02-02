package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.ATA.module.Modules;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class VoltageTest extends Robot implements Modules {

    public void teleopInit() {
        test.enable();
        hallEffect.enable();
    }

    public void teleopPeriodic() {
        test.teleop();
        SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
    }
}
