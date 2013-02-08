package edu.ATA.twolf;

import edu.ATA.autonomous.Gordian;
import edu.ATA.bindings.CommandBind;
import edu.ATA.commands.AlignCommand;
import edu.ATA.commands.BangBangCommand;
import edu.ATA.commands.ShootCommand;
import edu.ATA.commands.ShooterAlignCommand;
import edu.ATA.main.Logger;
import edu.ATA.main.PortMap;
import edu.ATA.main.Robot;
import edu.ATA.module.actuator.SolenoidModule;
import edu.ATA.module.driving.ArcadeBinding;
import edu.ATA.module.driving.GearShift;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.HallEffectModule;
import edu.ATA.module.sensor.PotentiometerModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.speedcontroller.SpikeRelay;
import edu.ATA.module.speedcontroller.SpikeRelayModule;
import edu.ATA.module.subsystems.AlignmentSystem;
import edu.ATA.module.subsystems.ShiftingDrivetrain;
import edu.ATA.module.subsystems.Shooter;
import edu.ATA.module.target.BangBangModule;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class TheWolf extends Robot implements PortMap {

    // Setpoint for shooter!
    private static double SETPOINT = 4500;
    private static double HIGH = 4.5;
    private static double MED_HIGH = 4;
    private static double MEDIUM = 3.5;
    private static double LOW = 3;

    static {
        updateValues();
    }

    private static void updateValues() {
        SETPOINT = Preferences.getInstance().getDouble("ShooterSetpoint", SETPOINT);
        HIGH = Preferences.getInstance().getDouble("HighPosition", HIGH);
        MED_HIGH = Preferences.getInstance().getDouble("MedHighPosition", MED_HIGH);
        MEDIUM = Preferences.getInstance().getDouble("MediumPosition", MEDIUM);
        LOW = Preferences.getInstance().getDouble("LowPosition", LOW);
    }
    // Setpoint for shooter!
    private static TheWolf theWolf;
    private final SpeedControllerModule leftBack = new SpeedControllerModule(new Victor(DRIVE[0])),
            leftFront = new SpeedControllerModule(new Victor(DRIVE[1])),
            rightBack = new SpeedControllerModule(new Victor(DRIVE[2])),
            rightFront = new SpeedControllerModule(new Victor(DRIVE[3]));
    private final SolenoidModule firstGear = new SolenoidModule(new Solenoid(GEAR_1)),
            secondGear = new SolenoidModule(new Solenoid(GEAR_2));
    private final SolenoidModule loader = new SolenoidModule(new Solenoid(LOADER_PORT)),
            reloader = new SolenoidModule(new Solenoid(RELOADER_PORT));
    private final SolenoidModule shortAlign = new SolenoidModule(new Solenoid(SHORT_ALIGN_PORT)),
            longAlign = new SolenoidModule(new Solenoid(LONG_ALIGN_PORT)),
            staticAlign = new SolenoidModule(new Solenoid(STATIC_ALIGN_PORT));
    private final SpeedControllerModule shooterAligner = new SpeedControllerModule(new Victor(SHOOTER_ALIGNMENT_PORT));
    private final PotentiometerModule shooterAngle = new PotentiometerModule(new AnalogChannel(SHOOTER_POSITION));
    private final SpeedControllerModule shooter = new SpeedControllerModule(new Talon(SHOOTER_PORT));
    private final HallEffectModule hallEffect = new HallEffectModule(new DigitalInput(HALLEFFECT_PORT));
    private final RobotDriveModule drive = new RobotDriveModule(new RobotDrive(leftFront, leftBack, rightFront, rightBack));
    private final XboxController WOLF_CONTROL = new XboxController(new Joystick(JOYSTICK_1));
    private final XboxController WOLF_SHOT_CONTROL = new XboxController(new Joystick(JOYSTICK_2));
    /*
     * Subsystems 
     */
    private final ShiftingDrivetrain WOLF_DRIVE = new ShiftingDrivetrain(drive, firstGear, secondGear);
    private final Shooter WOLF_SHOOT = new Shooter(loader, reloader, shooterAngle, shooterAligner);
    private final BangBangModule WOLF_SHOOTER = new BangBangModule(hallEffect, shooter);
    private final AlignmentSystem WOLF_ALIGN = new AlignmentSystem(shortAlign, longAlign, staticAlign);

    public static Robot fetchTheHound() {
        return (theWolf == null) ? (theWolf = new TheWolf()) : (theWolf);
    }

    private TheWolf() {
    }

    public void robotInit() {
        // Refilling capacity using spike relay always on
        SpikeRelayModule compressor = new SpikeRelayModule(new Relay(COMPRESSOR));
        compressor.enable();
        compressor.set(SpikeRelay.FORWARD);
        // Refilling capacity using spike relay always on
        Preferences.getInstance().putDouble("ShooterSetpoint", SETPOINT);
        Preferences.getInstance().putDouble("HighPosition", HIGH);
        Preferences.getInstance().putDouble("MedHighPosition", MED_HIGH);
        Preferences.getInstance().putDouble("MediumPosition", MEDIUM);
        Preferences.getInstance().putDouble("LowPosition", LOW);
    }

    public void disabledInit() {
        updateValues();
        WOLF_SHOOT.disable();
        WOLF_SHOOTER.disable();
        WOLF_ALIGN.disable();
        WOLF_CONTROL.disable();
        WOLF_DRIVE.disable();
    }

    public void disabledPeriodic() {
    }

    public void autonomousInit() {
        updateValues();
        WOLF_DRIVE.enable();
        drive.setSafetyEnabled(false);
        WOLF_SHOOT.enable();
        WOLF_SHOOTER.enable();
        WOLF_ALIGN.enable();
        Gordian.ensureInit(WOLF_DRIVE, WOLF_SHOOT, WOLF_SHOOTER, WOLF_ALIGN);
        try {
            Gordian.run("auto/" + Preferences.getInstance().getString("AutonomousMode", "auto") + ".txt");
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.log(Logger.Urgency.URGENT, "AUTO DID NOT RUN");
        }
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit() {
        updateValues();
        WOLF_SHOOT.enable();
        WOLF_SHOOTER.enable();
        WOLF_ALIGN.enable();
        WOLF_CONTROL.enable();
        WOLF_SHOT_CONTROL.enable();
        WOLF_DRIVE.enable();

        WOLF_CONTROL.removeAllBinds();
        WOLF_SHOT_CONTROL.removeAllBinds();
        // Driving //
        WOLF_CONTROL.bindAxis(XboxController.RIGHT_X, new ArcadeBinding(drive, ArcadeBinding.ROTATE));
        WOLF_CONTROL.bindAxis(XboxController.LEFT_Y, new ArcadeBinding(drive, ArcadeBinding.FORWARD));
        WOLF_CONTROL.bindWhenPressed(XboxController.RIGHT_BUMPER, new GearShift(secondGear, firstGear));
        // Alignment //
        WOLF_CONTROL.bindWhenPressed(XboxController.Y, new AlignCommand(WOLF_ALIGN, AlignCommand.COLLAPSE));
        WOLF_CONTROL.bindWhenPressed(XboxController.B, new AlignCommand(WOLF_ALIGN, AlignCommand.LONG));
        WOLF_CONTROL.bindWhenPressed(XboxController.A, new AlignCommand(WOLF_ALIGN, AlignCommand.SHORT));
        // Shooting //
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.RIGHT_BUMPER, new ShootCommand(WOLF_SHOOT));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.START, new BangBangCommand(WOLF_SHOOTER, SETPOINT));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.BACK, new BangBangCommand(WOLF_SHOOTER, 0));
        WOLF_SHOOTER.setSetpoint(SETPOINT);
        // Shooter position //
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.Y, new ShooterAlignCommand(WOLF_SHOOT, HIGH));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.X, new ShooterAlignCommand(WOLF_SHOOT, MED_HIGH));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.B, new ShooterAlignCommand(WOLF_SHOOT, MEDIUM));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.A, new ShooterAlignCommand(WOLF_SHOOT, LOW));
        WOLF_SHOT_CONTROL.bindWhilePressed(XboxController.LEFT_BUMPER, new CommandBind() {
            public void run() {
                // Manual adjustment when left bumper is pressed
                shooterAligner.set(WOLF_SHOT_CONTROL.getTriggers());
            }
        });
    }

    public void teleopPeriodic() {
        WOLF_CONTROL.doBinds();
        SmartDashboard.putBoolean("PastSetpoint", WOLF_SHOOTER.pastSetpoint());
        SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
    }

    public void testInit() {
        updateValues();
        hallEffect.enable();
    }

    public void testPeriodic() {
        SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
    }
}
