package ata2014.main;

import edu.first.main.Constants;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;
import edu.first.robot.IterativeRobotAdapter;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.log.Logger;
import org.gordian.scope.GordianScope;

/**
 * Team 4334's main robot code starting point. Everything that happens is
 * derived from this class.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Robot extends IterativeRobotAdapter implements Constants {

    private final File AUTONOMOUS = new File("2014 Autonomous.txt");
    private final Subsystem FULL_ROBOT = new SubsystemBuilder()
            .add(joysticks)
            .add(drivetrainSubsystem)
            .add(loader)
            .add(shooter)
            .toSubsystem();

    public Robot() {
        super("2014 Robot");
    }

    public void init() {
        Logger.addLogToAll(new Logger.FileLog(new File("2014 Log File.txt")));

        Logger.getLogger(this).warn("Robot is initializing");
        FULL_ROBOT.init();
        Logger.getLogger(this).warn("Robot is ready");
    }

    public void initAutonomous() {
        Logger.getLogger(this).info("Autonomous starting...");
        GordianScope autonomous = new GordianScope();
        autonomous.run(TextFiles.getTextFromFile(AUTONOMOUS));
    }

    public void initTeleoperated() {
        Logger.getLogger(this).info("Teleoperated starting...");

        joysticks.enable();
        drivetrainSubsystem.enable();
        loader.enable();

        joystick1.addAxisBind(drivetrain.getArcade(joystick1.getLeftY(), joystick1.getRightX()));
    }

    public void initDisabled() {
        Logger.getLogger(this).info("Disabled starting...");
        FULL_ROBOT.disable();
    }

    public void initTest() {
        Logger.getLogger(this).info("Test starting...");
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
        Logger.getLogger(this).warn("Autonomous finished");
    }

    public void endTeleoperated() {
        joystick1.clearBinds();
        joystick2.clearBinds();

        Logger.getLogger(this).warn("Teleoperated finished");
    }
}
