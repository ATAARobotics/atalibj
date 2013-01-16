package test;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Team 4334
 */
public class GearShiftingShooter extends PneumaticShooter {

    private boolean gearOn = false;

    public String name() {
        return "Gear Shifting Shooter";
    }

    public void robotInit() throws Error {
        super.robotInit();
        SmartDashboard.putNumber("Gear", gearOn ? 2 : 1);
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        if (controller.getAButton()) {
            gearOn = !gearOn;
            SmartDashboard.putNumber("Gear", gearOn ? 2 : 1);
        }
        gearShifter.set(gearOn);
    }
}
