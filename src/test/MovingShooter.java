package test;

/**
 *
 * @author Team 4334
 */
public class MovingShooter extends TrackedShooter {

    public String name() {
        return "Moving Shooter";
    }

    public void robotInit() throws Error {
        super.robotInit();
        robotDrive.enable();
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        robotDrive.arcadeDrive(controller.getRightDistanceFromMiddle(), controller.getLeftX());
    }
}
