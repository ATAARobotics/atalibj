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
        shooterMotor.enable();
        hallEffect.enable();
        bangbang.enable();
        bangbang.setSetpoint(4500);
    }

    public void teleopPeriodic() {
        SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
    }
}
