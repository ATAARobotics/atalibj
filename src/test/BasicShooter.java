package test;

import edu.ATA.main.DefaultRobot;
import edu.ATA.main.DriverstationInfo;
import edu.ATA.main.UserInfo;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class BasicShooter extends DefaultRobot {

    protected SpeedControllerModule shooter = new SpeedControllerModule(new Victor(5));
    protected XboxController controller = new XboxController(new Joystick(1));

    public String name() {
        return "Basic Shooter";
    }

    public void robotInit() throws Error {
        shooter.enable();
        controller.enable();
    }

    public void teleopPeriodic() throws Error {
        if (DriverstationInfo.getMatchTime() % 1 <= 0.1) {
            UserInfo.updateTransferRate();
        }
        shooter.set(controller.getTriggers());
    }
}
