package test;

/**
 *
 * @author joel
 */
public class GearedShooter extends GearedDrive {

    public void robotInit() throws Error {
        super.robotInit();
        shooter.enable();
        controller.enable();
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        shooter.set(controller.getTriggers());
    }
}
