package edu.ATA.test;

import edu.ATA.main.Logger;
import edu.ATA.main.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class RobotSelector extends Robot {

    private static final SendableChooser robotSelector = new SendableChooser();

    static {
        robotSelector.addObject("Drivetrain", new DrivetrainTest());
        robotSelector.addObject("Shooter", new ShooterTest());
        robotSelector.addObject("Voltage", new VoltageTest());
        robotSelector.addObject("HallEffect", new HallEffectTest());
        robotSelector.addObject("SpeedControllerTest", new SpeedControllersTest());
        SmartDashboard.putData("RobotSelector", robotSelector);
    }
    private Robot current;

    public RobotSelector() {
    }

    private void update() {
        Robot prev = current;
        current = (Robot) robotSelector.getSelected();
        if(prev == null || !prev.equals(current)) {
            if(prev != null) {
                prev.disabledInit();
            }
            current.robotInit();
        }
        Logger.log(Logger.Urgency.STATUSREPORT, "Robot="+current.getClass().getName());
    }

    public void robotInit() {
        Timer.delay(4);
        update();
        current.robotInit();
    }

    public void disabledInit() {
        update();
        current.disabledInit();
    }

    public void disabledPeriodic() {
        current.disabledPeriodic();
    }

    public void autonomousInit() {
        update();
        current.autonomousInit();
    }

    public void autonomousPeriodic() {
        current.autonomousPeriodic();
    }

    public void teleopInit() {
        update();
        current.teleopInit();
    }

    public void teleopPeriodic() {
        current.teleopPeriodic();
    }

    public void testInit() {
        update();
        current.testInit();
    }

    public void testPeriodic() {
        current.testPeriodic();
    }
}
