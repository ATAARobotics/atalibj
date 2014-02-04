package ata2014.main;

import com.sun.squawk.microedition.io.FileConnection;
import edu.first.commands.common.SetOutput;
import edu.first.main.Constants;
import edu.first.module.joysticks.XboxController;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;
import edu.first.robot.IterativeRobotAdapter;
import edu.first.util.DriverstationInfo;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.dashboard.NumberDashboard;
import edu.first.util.log.Logger;
import java.io.IOException;
import javax.microedition.io.Connector;
import org.gordian.scope.GordianScope;

/**
 * Team 4334's main robot code starting point. Everything that happens is
 * derived from this class.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Robot extends IterativeRobotAdapter implements Constants {

    private final File AUTONOMOUS = new File("2014 Autonomous.txt");
    private final NumberDashboard winchShootingPosition = new NumberDashboard("WinchShootingPositition",
            Preferences.getInstance().getDouble("WinchShootingPosition", 0));
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
        Logger.getLogger(this).warn("Robot is initializing");
        // incrementing log file management - new log file every reboot
        for (int x = 1; true; x++) {
            File file = new File("Log " + x + ".txt");
            try {
                FileConnection log = (FileConnection) Connector.open(file.getFullPath(), Connector.READ);
                if (!log.exists()) {
                    Logger.addLogToAll(new Logger.FileLog(file));
                    log.close();
                    break;
                }

                log.close();
            } catch (IOException e) {
                Logger.getLogger(this).error("Creating a log file failed - see stack trace", e);
            }
        }

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

        if (DriverstationInfo.getBatteryVoltage() < 12) {
            Logger.getLogger(DriverstationInfo.class).warn("Battery bellow 12V - please replace");
        }

        joysticks.enable();
        drivetrainSubsystem.enable();
        loader.enable();

        joystick1.addAxisBind(drivetrain.getArcade(joystick1.getLeftDistanceFromMiddle(), joystick1.getRightX()));
        joystick1.addWhenPressed(XboxController.A, new Runnable() {
            public void run() {
                leftArmReset.enable();
                rightArmReset.enable();
            }
        });
        joystick1.addWhenReleased(XboxController.A, new Runnable() {
            public void run() {
                leftArmReset.disable();
                rightArmReset.disable();
            }
        });
        joystick2.addAxisBind(XboxController.TRIGGERS, loaderMotors);
        joystick2.addWhenPressed(XboxController.A, new SetOutput(winchController, winchShootingPosition));
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
