package ata2014.main;

import edu.first.main.Constants;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;
import edu.first.robot.IterativeRobotAdapter;

/**
 * Team 4334's main robot code starting point. Everything that happens is
 * derived from this class.
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

    public Robot() {
        super("2014 Robot");
    }

    public void init() {
        FULL_ROBOT.init();
    }

    public void initAutonomous() {
    }

    public void initTeleoperated() {
        joystick1.addAxisBind(drivetrain.getArcade(joystick1.getLeftY(), joystick1.getRightX()));
    }

    public void initDisabled() {
    }

    public void initTest() {
    }

    public void periodicAutonomous() {
    }

    public void periodicTeleoperated() {
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
