package ata2014.main;

import ata2014.commands.AddButtonBind;
import ata2014.commands.DisableModule;
import ata2014.commands.EnableModule;
import ata2014.commands.RemoveButtonBind;
import edu.first.commands.common.ReverseDualActionSolenoid;
import edu.first.commands.common.SetDualActionSolenoid;
import edu.first.commands.common.SetOutput;
import edu.first.identifiers.Function;
import edu.first.identifiers.TransformedOutput;
import edu.first.main.Constants;
import edu.first.module.actuators.DualActionSolenoid;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
            .add(backLoader)
            .add(shooter)
            .add(drivingPID)
            .add(winchController)
            .add(compressor)
            .toSubsystem();

    public Robot() {
        super("2014 Robot");
    }

    public void init() {
        Logger.getLogger(this).warn("Robot is initializing");
        File logFile = new File("Log.txt");
        TextFiles.writeAsFile(logFile, "Log File:");
        Logger.addLogToAll(new Logger.FileLog(logFile));

        // Apply function to driving algorithm
        joystick1.changeAxis(XboxController.LEFT_FROM_MIDDLE, new Function() {
            public double F(double in) {
                return drivingSensitivity * (MathUtils.pow(in, 3)) + (1 - drivingSensitivity) * in;
            }
        });

        drivetrain.setReversedTurn(true);

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
        backLoader.enable();
        shooter.enable();
        compressor.enable();

        loaderPiston.set(DualActionSolenoid.Direction.LEFT);
        shifter.set(DualActionSolenoid.Direction.LEFT);

        // Driving
        if (Preferences.getInstance().getBoolean("DRIVINGPIDON", false)) {
            drivingPID.enable();
            joystick1.addAxisBind(drivingPID.getArcade(joystick1.getLeftDistanceFromMiddle(), joystick1.getRightX()));
        } else {
            joystick1.addAxisBind(drivetrain.getArcade(joystick1.getLeftDistanceFromMiddle(), joystick1.getRightX()));
        }

        joystick1.addWhenPressed(XboxController.B, new ReverseDualActionSolenoid(shifter));
        // Shoot
        joystick1.addWhenPressed(XboxController.A, new SetDualActionSolenoid(winchRelease, DualActionSolenoid.Direction.RIGHT));
        joystick1.addWhenPressed(XboxController.A, new SetDualActionSolenoid(loaderPiston, DualActionSolenoid.Direction.RIGHT));
        if (Preferences.getInstance().getBoolean("SHOOTINGNEUTRAL", false)) {
            // after shooting, default to neutral position
            joystick1.addWhenReleased(XboxController.A, new SetOutput(winchController, winchNeutralPosition));
        }

        // Move loader
        joystick1.addWhenPressed(XboxController.X, new ReverseDualActionSolenoid(loaderPiston));
        joystick2.addWhenPressed(XboxController.X, new ReverseDualActionSolenoid(loaderPiston));

        joystick2.addAxisBind(XboxController.TRIGGERS, new TransformedOutput(loaderMotors, new Function.ProductFunction(Preferences.getInstance().getDouble("LoaderLimit", 0.5))));

        joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new SetDualActionSolenoid(backLoaderPiston, DualActionSolenoid.Direction.LEFT));
        joystick2.addWhenPressed(XboxController.LEFT_BUMPER, new SetDualActionSolenoid(backLoaderPiston, DualActionSolenoid.Direction.RIGHT));

        BindingJoystick.ButtonBind winchOn = new BindingJoystick.WhilePressed(joystick2.getA(), new SetOutput(winchMotor, 1));
        BindingJoystick.ButtonBind winchOff = new BindingJoystick.WhenReleased(joystick2.getA(), new SetOutput(winchMotor, 0));
        BindingJoystick.ButtonBind engageDog = new BindingJoystick.WhenPressed(joystick2.getA(),
                new SetDualActionSolenoid(winchRelease, DualActionSolenoid.Direction.LEFT));
        if (Preferences.getInstance().getBoolean("WINCHCONTROLLERON", false)) {
            winchController.enable();
            // Bring winch back
            joystick2.addWhenPressed(XboxController.A, new SetOutput(winchController, winchShootingPosition));

            // Turn on manual winch control
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new DisableModule(winchController));
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new AddButtonBind(joystick2, winchOn));
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new AddButtonBind(joystick2, winchOff));
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new AddButtonBind(joystick2, engageDog));
            // Turn off manual winch control
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new RemoveButtonBind(joystick2, winchOn));
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new RemoveButtonBind(joystick2, winchOff));
            joystick2.addWhenReleased(XboxController.RIGHT_BUMPER, new RemoveButtonBind(joystick2, engageDog));
            joystick2.addWhenReleased(XboxController.RIGHT_BUMPER, new EnableModule(winchController));
        } else {
            joystick2.addButtonBind(winchOn);
            joystick2.addButtonBind(winchOff);
            joystick2.addButtonBind(engageDog);
        }
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
        SmartDashboard.putNumber("LeftSpeed", leftEncoder.getRate());
        SmartDashboard.putNumber("RightSpeed", rightEncoder.getRate());
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
