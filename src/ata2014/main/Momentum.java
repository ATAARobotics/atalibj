package ata2014.main;

import ata2014.commands.DisableModule;
import ata2014.commands.EnableModule;
import edu.first.commands.common.ReverseDualActionSolenoid;
import edu.first.commands.common.SetDualActionSolenoid;
import edu.first.commands.common.SetOutput;
import edu.first.main.Constants;
import edu.first.module.Module;
import edu.first.module.actuators.DualActionSolenoid;
import edu.first.module.joysticks.XboxController;
import edu.first.module.subsystems.Subsystem;
import edu.first.robot.IterativeRobotAdapter;
import edu.first.util.File;
import edu.first.util.TextFiles;
import edu.first.util.log.Logger;

/**
 * Team 4334's main robot code starting point. Everything that happens is
 * derived from this class.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Momentum extends IterativeRobotAdapter implements Constants {

    private final Subsystem TELEOP_MODULES = new Subsystem(new Module[]{
        joysticks, compressor, drive, shifters, winch, loader, backLoader
    });
    private final Subsystem AUTO_MODULES = new Subsystem(new Module[]{
        drive, winch, loader, backLoader
    });
    private final Subsystem ALL_MODULES = new Subsystem(new Module[]{TELEOP_MODULES, AUTO_MODULES,
        // Modules that are turned on conditionally
        drivingPID,
        winchBack, winchPosition, winchController
    });

    public Momentum() {
        super("Momentum");
        Logger.displayLCDMessage("DO NOT ENABLE");
    }

    public void init() {
        Logger.getLogger(this).info("Robot initializing...");
        TextFiles.writeAsFile(logFile, "--- Log file ---");
        Logger.addLogToAll(new Logger.FileLog(logFile));

        // settings
        drivetrain.setReversedTurn(true);

        ALL_MODULES.init();

        // add joystick binds
        addBinds();
        Logger.displayLCDMessage("Okay to enable");
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
     .... Left Bumper - 
     .... Right Bumper - 
     .... Triggers - 

     Joystick 2
     .... A - Bring winch back
     .... B - Stop winch
     .... X - Open arms
     .... Y - 
     .... Left Stick - 
     .... Right Stick - 
     .... Left Bumper - Back loader up
     .... Right Bumper - Back loader down
     .... Triggers - Arm control

     */
    private void addBinds() {
        if (DRIVING_PID && CONTROL_STYLE.equalsIgnoreCase("arcade")) {
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
        joystick1.addWhenPressed(XboxController.A, new SetDualActionSolenoid(winchRelease, DualActionSolenoid.Direction.RIGHT));
        joystick1.addWhenPressed(XboxController.A, new SetDualActionSolenoid(loaderPiston, DualActionSolenoid.Direction.RIGHT));
        joystick1.addWhenPressed(XboxController.B, new ReverseDualActionSolenoid(shifters));
        joystick1.addWhenPressed(XboxController.X, new ReverseDualActionSolenoid(loaderPiston));

        joystick2.addWhenPressed(XboxController.A, new EnableModule(winchBack));
        joystick2.addWhenPressed(XboxController.B, new DisableModule(winchBack));
        joystick2.addWhenPressed(XboxController.B, new SetOutput(winchMotor, 0));
        joystick2.addWhenPressed(XboxController.X, new ReverseDualActionSolenoid(loaderPiston));
        joystick2.addWhenPressed(XboxController.LEFT_BUMPER, new SetDualActionSolenoid(backLoaderPiston, DualActionSolenoid.Direction.LEFT));
        joystick2.addWhenPressed(XboxController.RIGHT_BUMPER, new SetDualActionSolenoid(backLoaderPiston, DualActionSolenoid.Direction.RIGHT));
        joystick2.addAxisBind(XboxController.TRIGGERS, loaderMotors);
        Logger.getLogger(this).info("Binds added");
    }

    public void initTeleoperated() {
        TELEOP_MODULES.enable();

        if (DRIVING_PID) {
            drivingPID.enable();
        }
        if (WINCH_CONTROL) {
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
    }

    public void endTeleoperated() {
        TELEOP_MODULES.disable();
        Logger.getLogger(this).info("Teleop finished");
    }

    public void initAutonomous() {
        Logger.getLogger(this).info("Autonomous starting...");
        AUTO_MODULES.enable();
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
        settings.reload();
    }
}
