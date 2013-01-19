package test;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 * @author Team 4334
 */
public class CShootingDrive extends BGearShiftingDrive {

    public void robotInit() throws Error {
        super.robotInit();
        shooter.enable();
        LiveWindow.addActuator("N/A", "Shooter", shooterMotor);
    }

    public void teleopPeriodic() throws Error {
        super.teleopPeriodic();
        shooter.set(controller.getTriggers());
        LiveWindow.run();
    }
}
