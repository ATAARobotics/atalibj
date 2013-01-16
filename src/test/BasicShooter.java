package test;

import edu.ATA.main.DefaultRobot;
import edu.ATA.main.UserInfo;
import edu.ATA.module.Modules;

public class BasicShooter extends DefaultRobot implements Modules {

    public String name() {
        return "Basic Shooter";
    }

    public void robotInit() throws Error {
        shooter.enable();
        controller.enable();
    }

    public void teleopPeriodic() throws Error {
        if (System.currentTimeMillis() % 1000 <= 25) {
            UserInfo.updateTransferRate();
        }
        UserInfo.updateGameTime();
        shooter.set(controller.getTriggers());
    }
}
