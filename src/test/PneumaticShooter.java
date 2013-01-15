package test;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Team 4334
 */
public class PneumaticShooter extends MovingShooter {
    
    protected Solenoid solenoid = new Solenoid(1);

    public String name() {
        return "Pneumatic Shooter";
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        solenoid.set(controller.getRightBumper());
    }
}
