package ata2014.main;

import ata2014.commands.AddAxisBind;
import ata2014.commands.DisableModule;
import ata2014.commands.EnableModule;
import ata2014.commands.RemoveAxisBind;
import com.sun.squawk.microedition.io.FileConnection;
import edu.first.commands.common.SetOutput;
import edu.first.commands.common.SetSolenoid;
import edu.first.identifiers.Function;
import edu.first.main.Constants;
import edu.first.module.Module;
import edu.first.module.joysticks.BindingJoystick;
import edu.first.module.joysticks.XboxController;
import edu.first.module.subsystems.Subsystem;
import edu.first.module.subsystems.SubsystemBuilder;
import edu.first.robot.IterativeRobotAdapter;
import edu.first.util.DriverstationInfo;
import edu.first.util.File;
import edu.first.util.MathUtils;
import edu.first.util.TextFiles;
import edu.first.util.dashboard.NumberDashboard;
import edu.first.util.log.Logger;
import java.io.IOException;
import javax.microedition.io.Connector;

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
    private final NumberDashboard winchNeutralPosition = new NumberDashboard("WinchNeutralPositition",
            Preferences.getInstance().getDouble("WinchNeutralPosition", 5));
    private final double drivingSensitivity = Preferences.getInstance().getDouble("DrivingSensitivity", 0.5);
    private final Subsystem FULL_ROBOT = new SubsystemBuilder()
            .add(joysticks)
            .add(drivetrainSubsystem)
            .add(loader)
            .add(shooter)
            .add(drivingPID)
            .add(leftArmReset).add(rightArmReset)
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
                FileConnection log = (FileConnection) Connector.open(file.getFullPath(), Connector.READ_WRITE);
                if (!log.exists()) {
                    TextFiles.writeAsFile(file, "LOG FILE\n");
                    Logger.addLogToAll(new Logger.FileLog(file));
                    break;
                }

                log.close();
            } catch (IOException e) {
                Logger.getLogger(this).error("Creating a log file failed - see stack trace", e);
            }
        }

        // Apply function to driving algorithm
        joystick1.changeAxis(XboxController.LEFT_FROM_MIDDLE, new Function() {
            public double F(double in) {
                return drivingSensitivity * (MathUtils.pow(in, 3)) + (1 - drivingSensitivity) * in;
            }
        });

        FULL_ROBOT.init();
        Logger.getLogger(this).warn("Robot is ready");
    }

    public void initAutonomous() {
        Logger.getLogger(this).info("Autonomous starting...");
        Autonomous autonomous = new Autonomous();
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
        shooter.enable();

        // Driving
        joystick1.addAxisBind(drivetrain.getArcade(joystick1.getLeftDistanceFromMiddle(), joystick1.getRightX()));

        // Reset the arms
        joystick1.addWhenPressed(XboxController.A, new EnableModule(new Module[]{leftArmReset, rightArmReset}));
        joystick1.addWhenReleased(XboxController.A, new DisableModule(new Module[]{leftArmReset, rightArmReset}));

        // Shoot
        joystick1.addWhenPressed(XboxController.A, new SetSolenoid(winchRelease, true));
        joystick1.addWhenReleased(XboxController.A, new SetSolenoid(winchRelease, false));
        // after shooting, default to neutral position
        joystick1.addWhenReleased(XboxController.A, new SetOutput(winchController, winchNeutralPosition));

        // Move loader
        joystick2.addAxisBind(XboxController.TRIGGERS, loaderMotors);

        // Bring winch back
        joystick2.addWhenPressed(XboxController.A, new SetOutput(winchController, winchShootingPosition));

        // Turn on manual winch control
        final BindingJoystick.AxisBind winchManual = new BindingJoystick.AxisBind(joystick2.getRightY(), winchMotor);
        joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new DisableModule(winchController));
        joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new AddAxisBind(joystick2, winchManual));
        // Turn off manual winch control
        joystick2.addWhenReleased(XboxController.RIGHT_BUMPER, new RemoveAxisBind(joystick2, winchManual));
        joystick2.addWhenReleased(XboxController.RIGHT_BUMPER, new EnableModule(winchController));
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
