package edu.ATA.test;

import edu.ATA.main.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class RobotSelector extends Robot {

    private static final SendableChooser robotSelector = new SendableChooser();

    static {
        robotSelector.addObject("Drivetrain", new DrivetrainTest());
        robotSelector.addObject("Shooter", new ShooterTest());
        robotSelector.addObject("Voltage", new VoltageTest());
        SmartDashboard.putData("RobotSelector", robotSelector);
    }

    public RobotSelector() {
    }
    
    private Robot getSelected() {
        return (Robot) robotSelector.getSelected();
    }

    public void robotInit() {
        getSelected().robotInit();
    }

    public void disabledInit() {
        getSelected().disabledInit();
    }

    public void disabledPeriodic() {
        getSelected().disabledPeriodic();
    }

    public void autonomousInit() {
        getSelected().autonomousInit();
    }

    public void autonomousPeriodic() {
        getSelected().autonomousPeriodic();
    }

    public void teleopInit() {
        getSelected().teleopInit();
    }

    public void teleopPeriodic() {
        getSelected().teleopPeriodic();
    }

    public void testInit() {
        getSelected().testInit();
    }

    public void testPeriodic() {
        getSelected().testPeriodic();
    }
}
