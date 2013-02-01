package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.ATA.module.Modules;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class HallEffectTest extends Robot implements Modules {

    public void robotInit() {
        hallEffect.enable();
    }

    public void teleopPeriodic() {
        SmartDashboard.putBoolean("HallEffect", hallEffect.isPolarized());
        SmartDashboard.putNumber("HallEffectCount", hallEffect.getCount());
        SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
    }
}
