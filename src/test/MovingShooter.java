package test;

import edu.ATA.module.driving.RobotDriveModule;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Team 4334
 */
public class MovingShooter extends TrackedShooter {

    protected RobotDriveModule robotDrive = new RobotDriveModule(new RobotDrive(1, 2, 3, 4));

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
