package test;

import edu.ATA.main.DefaultRobot;
import edu.ATA.module.Modules;

/**
 *
 * @author Team 4334
 */
public class BasicDrive extends DefaultRobot implements Modules {

    public String name() {
        return "Basic Drive";
    }

    public void robotInit() throws Error {
        drivetrain.enable();
    }

    public void teleopPeriodic() throws Error {
        drivetrain.drive();
    }
}
