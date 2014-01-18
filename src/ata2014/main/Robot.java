package ata2014.main;

import edu.first.main.Constants;
import edu.first.module.actuators.VictorModule;
import edu.first.module.joysticks.BindingJoystick;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;
import edu.first.robot.IterativeRobotAdapter;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Robot extends IterativeRobotAdapter implements Constants {

    private final Subsystem FULL_ROBOT = new SubsystemBuilder()
            .add(joystick1).add(joystick2)
            .add(frontLeftDrive).add(backLeftDrive)
            .add(frontRightDrive).add(backRightDrive)
            .add(drivetrain)
            .toSubsystem();
    private final VictorModule loadmoter = new VictorModule(5);
    private final BindingJoystick.DualAxisBind drivingBind = new BindingJoystick.DualAxisBind(
            joystick1.getLeftY(), joystick1.getRightX()) {
                public void doBind(double axis1, double axis2) {
                    drivetrain.arcadeDrive(axis1, axis2);
                }
            };

    public Robot() {
        super("2014 Robot");
    }

    public static int getBACK_LEFT_DRIVE() {
        return BACK_LEFT_DRIVE;
    }

    public void init() {
        FULL_ROBOT.init();
    }

    public void initAutonomous() {
    }

    public void initTeleoperated() {
        joystick1.addAxisBind(drivingBind);
    }

    public void initDisabled() {
    }

    public void initTest() {
    }

    public void periodicAutonomous() {
    }

    public void periodicTeleoperated() {
        loadmoter.setRate(joystick1.getTriggerValue());
        joystick1.doBinds();
        joystick2.doBinds();
    }

    public void periodicDisabled() {
    }

    public void periodicTest() {
    }

    public void endAutonomous() {
    }

    public void endTeleoperated() {
        joystick1.clearBinds();
        joystick2.clearBinds();
    }
}
