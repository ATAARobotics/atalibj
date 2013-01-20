package test;

import edu.ATA.main.DefaultRobot;
import edu.ATA.module.Modules;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author joel
 */
public class ShooterTest extends DefaultRobot implements Modules {

    public String name() {
        return "Shooter Test";
    }

    public void robotInit() throws Error {
        bangBangShooter.enable();
        SmartDashboard.putNumber("ShooterSetpoint", 0);
    }

    public void teleopPeriodic() throws Error {
        bangBangShooter.setSetpoint(SmartDashboard.getNumber("ShooterSetpoint"));
    }
}
