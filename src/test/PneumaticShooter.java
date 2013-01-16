package test;

/**
 *
 * @author Team 4334
 */
public class PneumaticShooter extends MovingShooter {

    public String name() {
        return "Pneumatic Shooter";
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        feeder.set(controller.getRightBumper());
    }
}
