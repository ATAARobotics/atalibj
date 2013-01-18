package test;

import edu.ATA.main.DefaultRobot;
import edu.ATA.main.UserInfo;
import edu.ATA.module.Modules;

public class BasicDrive extends DefaultRobot implements Modules {

    public String name() {
        return "Basic Drive";
    }

    public void robotInit() throws Error {
        controller.enable();
        robotDrive.enable();
    }

    public void teleopPeriodic() throws Error {
        robotDrive.arcadeDrive(controller.getRightDistanceFromMiddle(), controller.getLeftX());
        if(System.currentTimeMillis() % 1000 < 15) {
            UserInfo.updateTransferRate();
        }
    }
}
