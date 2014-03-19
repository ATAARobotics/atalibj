package ata2014;

import edu.first.commands.common.DisableModule;
import edu.first.commands.common.EnableModule;
import edu.first.commands.common.ReverseDualActionSolenoid;
import edu.first.commands.common.SetDualActionSolenoid;
import edu.first.commands.common.SetOutput;
import edu.first.commands.common.SetSwitch;
import edu.first.identifiers.Function;
import edu.first.identifiers.TransformedOutput;
import edu.first.main.Constants;
import edu.first.module.Module;
import edu.first.module.actuators.DualActionSolenoid;
import edu.first.module.actuators.SpikeRelay;
import edu.first.module.joysticks.XboxController;
import edu.first.module.subsystems.Subsystem;
import edu.first.robot.IterativeRobotAdapter;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.dashboard.BooleanDashboard;
import edu.first.util.dashboard.NumberDashboard;
import edu.first.util.log.Logger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Momentum - our 2014 robot. For information about this robot, see
 * http://www.thebluealliance.com/team/4334/2014
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class Momentum extends IterativeRobotAdapter implements Constants {

    {
        // Let the drivers know that code is currently initializing
        Logger.displayLCDMessage("DO NOT ENABLE");
    }

    private final Subsystem TELEOP_MODULES = new Subsystem(new Module[]{
        joysticks, compressor, drive, shifters, winch, loader
    });
    private final Subsystem AUTO_MODULES = new Subsystem(new Module[]{
        drive, winch, loader
    });
    private final Subsystem ALL_MODULES = new Subsystem(new Module[]{TELEOP_MODULES, AUTO_MODULES,
        // Modules that are turned on conditionally
        drivingPID,
        winchBack, winchController,
        loaderController
    });

    private final BooleanDashboard winchLimitIndicator = new BooleanDashboard("Winch Limit", false);
    private final BooleanDashboard compressorIndicator = new BooleanDashboard("Compressor", false);
    private final NumberDashboard customSetpoint = new NumberDashboard("Custom Setpoint", LOADER_LOAD_SETPOINT.get());

    public Momentum() {
        super("Momentum");
    }

    public void init() {
        Logger.getLogger(this).info("Robot initializing...");
        TextFiles.writeAsFile(logFile, "--- Log file ---");
        Logger.addLogToAll(new Logger.FileLog(logFile));

        reloadSettings();
        drivetrain.setReversedTurn(true);

        ALL_MODULES.init();

        // add joystick binds
        addBinds();
        Logger.clearLCD();
        Logger.displayLCDMessage("Ready to enable");
    }

    /*
    
     BINDS
    
     Joystick 1
     .... A - Shoot
     .... B - Shift gear
     .... X - Open arms
     .... Y - 
     .... Left Stick - Driving
     .... Right Stick - Driving
     .... Left Bumper - Gear 1
     .... Right Bumper - Gear 2
     .... Triggers - 

     Joystick 2
     .... A - Bring winch back
     .... B - Stop winch
     .... X - Open arms
     .... Y - 
     .... Left Stick - 
     .... Right Stick - 
     .... Left Bumper - Loader up
     .... Right Bumper - Loader down
     .... Triggers - Arm control
     .... Start - Go to setpoint
     .... Back - Set setpoint

     */
    private void addBinds() {
        if (DRIVING_PID.getPosition() && CONTROL_STYLE.equalsIgnoreCase("arcade")) {
            joystick1.changeAxis(XboxController.LEFT_FROM_MIDDLE, drivingAlgorithm);
            joystick1.addAxisBind(drivingPID.getArcade(joystick1.getLeftDistanceFromMiddle(), joystick1.getRightX()));
        } else {
            if (CONTROL_STYLE.equalsIgnoreCase("arcade")) {
                joystick1.changeAxis(XboxController.LEFT_FROM_MIDDLE, drivingAlgorithm);
                joystick1.addAxisBind(drivetrain.getArcade(joystick1.getLeftDistanceFromMiddle(), joystick1.getRightX()));
            } else if (CONTROL_STYLE.equalsIgnoreCase("tank")) {
                joystick1.changeAxis(XboxController.LEFT_FROM_MIDDLE, drivingAlgorithm);
                joystick1.changeAxis(XboxController.RIGHT_FROM_MIDDLE, drivingAlgorithm);
                joystick1.addAxisBind(drivetrain.getTank(joystick1.getLeftDistanceFromMiddle(), joystick1.getRightDistanceFromMiddle()));
            }
        }
        joystick1.addWhenPressed(XboxController.A, new SetDualActionSolenoid(winchRelease, DualActionSolenoid.Direction.LEFT));
        joystick1.addWhenPressed(XboxController.A, new SetDualActionSolenoid(loaderPiston, DualActionSolenoid.Direction.RIGHT));
        joystick1.addWhenPressed(XboxController.A, new SetSwitch(winchLimitIndicator, false));
        joystick1.addWhenPressed(XboxController.B, new ReverseDualActionSolenoid(shifters));
        joystick1.addWhenPressed(XboxController.X, new ReverseDualActionSolenoid(loaderPiston));
        joystick1.addWhenPressed(XboxController.LEFT_BUMPER, new SetDualActionSolenoid(shifters, DualActionSolenoid.Direction.LEFT));
        joystick1.addWhenPressed(XboxController.RIGHT_BUMPER, new SetDualActionSolenoid(shifters, DualActionSolenoid.Direction.RIGHT));
        if (MAC_MODE) {
            joystick1.addWhenPressed(XboxController.LEFT_BUMPER, new EnableModule(winchBack));
            joystick1.addWhenPressed(XboxController.LEFT_BUMPER, new SetDualActionSolenoid(winchRelease, DualActionSolenoid.Direction.RIGHT));
            joystick1.addWhenPressed(XboxController.LEFT_BUMPER, new SetDualActionSolenoid(loaderPiston, DualActionSolenoid.Direction.LEFT));
            joystick1.addWhenPressed(XboxController.LEFT_BUMPER, new EnableModule(loaderController));
            joystick1.addWhenPressed(XboxController.LEFT_BUMPER, new SetOutput(loaderController, LOADER_STORE_SETPOINT));
            joystick1.addWhenPressed(XboxController.RIGHT_BUMPER, new EnableModule(loaderController));
            joystick1.addWhenPressed(XboxController.RIGHT_BUMPER, new SetOutput(loaderController, LOADER_LOAD_SETPOINT));
            joystick1.addWhenPressed(XboxController.RIGHT_BUMPER, new SetDualActionSolenoid(loaderPiston, DualActionSolenoid.Direction.RIGHT));
            joystick1.addWhenPressed(joystick1.getRawAxisAsButton(XboxController.TRIGGERS, 0.15), new DisableModule(loaderController));
            joystick1.addAxisBind(XboxController.TRIGGERS, new TransformedOutput(new TransformedOutput(loaderMotors,
                    new Function.ProductFunction(LOADER_MAX_SPEED)),
                    new Function.OppositeFunction()));
        }

        joystick2.addWhenPressed(XboxController.A, new EnableModule(winchBack));
        joystick2.addWhenPressed(XboxController.A, new SetDualActionSolenoid(winchRelease, DualActionSolenoid.Direction.RIGHT));
        joystick2.addWhenPressed(XboxController.B, new DisableModule(winchBack));
        joystick2.addWhenPressed(XboxController.B, new SetOutput(winchMotor, 0));
        joystick2.addWhenPressed(XboxController.X, new ReverseDualActionSolenoid(loaderPiston));
        joystick2.addWhenPressed(XboxController.BACK, new EnableModule(loaderController));
        joystick2.addWhenPressed(XboxController.BACK, new SetOutput(loaderController, customSetpoint));
        joystick2.addWhenPressed(XboxController.START, new SetOutput(customSetpoint, loaderPosition));
        joystick2.addWhenPressed(XboxController.START, new SetOutput(loaderController, loaderPosition));
        joystick2.addWhenPressed(XboxController.START, new EnableModule(loaderController));
        if (!MAC_MODE) {
            joystick2.addWhenPressed(XboxController.LEFT_BUMPER, new EnableModule(loaderController));
            joystick2.addWhenPressed(XboxController.LEFT_BUMPER, new SetOutput(loaderController, LOADER_STORE_SETPOINT));
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new EnableModule(loaderController));
            joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new SetOutput(loaderController, LOADER_LOAD_SETPOINT));
            joystick2.addWhenPressed(joystick2.getRawAxisAsButton(XboxController.TRIGGERS, 0.15), new DisableModule(loaderController));
            joystick2.addAxisBind(XboxController.TRIGGERS, new TransformedOutput(new TransformedOutput(loaderMotors,
                    new Function.ProductFunction(LOADER_MAX_SPEED)),
                    new Function.OppositeFunction()));
        }
        Logger.getLogger(this).info("Binds added");
    }

    public void initTeleoperated() {
        // reload settings, and update values for togglable modules
        reloadSettings();
        TELEOP_MODULES.enable();

        if (DRIVING_PID.getPosition()) {
            drivingPID.enable();
        }
        if (WINCH_CONTROL.getPosition()) {
            winchPosition.enable();
            winchController.enable();
        }

        loaderPiston.set(DualActionSolenoid.Direction.LEFT);
        shifters.set(DualActionSolenoid.Direction.LEFT);

        Logger.getLogger(this).info("Teleop started");
    }

    public void periodicTeleoperated() {
        joystick1.doBinds();
        joystick2.doBinds();

        compressorIndicator.set(compressorController.getDirection() != SpikeRelay.Direction.OFF);
        if (winchMotor.atLimit()) {
            winchLimitIndicator.set(true);
        }
        SmartDashboard.putNumber("Loader", loaderPosition.get());
        SmartDashboard.putBoolean("Gear", shifters.get() == DualActionSolenoid.Direction.LEFT);
    }

    public void endTeleoperated() {
        TELEOP_MODULES.disable();
        Logger.getLogger(this).info("Teleop finished");
    }

    public void initAutonomous() {
        Logger.getLogger(this).info("Autonomous starting...");
        AUTO_MODULES.enable();
        settings.reload();
        Autonomous autonomous = new Autonomous();
        autonomous.run(TextFiles.getTextFromFile(new File(settings.getString("AutoFile", "Autonomous.txt"))));
    }

    public void endAutonomous() {
        AUTO_MODULES.disable();
    }

    public void initDisabled() {
        Logger.getLogger(this).info("Disabled starting...");
        ALL_MODULES.disable();
    }

    public void endDisabled() {
    }

    public void reloadSettings() {
        settings.reload();
        
        loaderController.setTolerance(LOADER_TOLERANCE.get());
        loaderController.setP(LOADER_P.get());
        loaderController.setI(LOADER_I.get());
        loaderController.setD(LOADER_D.get());
        drivingPID.getPID().setP(DRIVING_P.get());
        drivingPID.getPID().setI(DRIVING_I.get());
        drivingPID.getPID().setD(DRIVING_D.get());
        drivingPID.setMaxSpeed(DRIVING_MAX_SPEED.get());
    }
}
