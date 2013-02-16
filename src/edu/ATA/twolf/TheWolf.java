package edu.ATA.twolf;

import edu.ATA.autonomous.Gordian;
import edu.ATA.commands.AlignCommand;
import edu.ATA.commands.AutoShoot;
import edu.ATA.commands.BangBangCommand;
import edu.ATA.commands.ShootCommand;
import edu.ATA.commands.ShooterAlignCommand;
import edu.ATA.commands.GearShift;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.twolf.subsystems.AlignmentSystem;
import edu.ATA.twolf.subsystems.ShiftingDrivetrain;
import edu.ATA.twolf.subsystems.Shooter;
import edu.first.bindings.AxisBind;
import edu.first.command.Command;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.driving.Function;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.driving.SideBinding;
import edu.first.module.sensor.DigitalLimitSwitchModule;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.sensor.GyroModule;
import edu.first.module.sensor.HallEffectModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.speedcontroller.SpikeRelay;
import edu.first.module.speedcontroller.SpikeRelayModule;
import edu.first.module.target.BangBangModule;
import edu.first.module.target.PIDModule;
import edu.first.robot.Robot;
import edu.first.robot.RobotAdapter;
import edu.first.utils.Logger;
import edu.first.utils.TransferRateCalculator;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
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
public class TheWolf extends RobotAdapter implements PortMap {

    // Setpoint for shooter!
    private static double SETPOINT = 4500;
    private static double DEFAULTSPEED = 0.5;
    private static double HIGH = 4.5;
    private static double MED_HIGH = 4;
    private static double MEDIUM = 3.5;
    private static double LOW = 3;
    private static boolean FOUR_WHEEL_DRIVE = true;

    static {
        updateValues();
    }

    private static void updateValues() {
        Logger.log(Logger.Urgency.STATUSREPORT, "Updating preferences...");
        SETPOINT = Preferences.getInstance().getDouble("ShooterSetpoint", SETPOINT);
        DEFAULTSPEED = Preferences.getInstance().getDouble("DefaultSpeed", DEFAULTSPEED);
        HIGH = Preferences.getInstance().getDouble("HighPosition", HIGH);
        MED_HIGH = Preferences.getInstance().getDouble("MedHighPosition", MED_HIGH);
        MEDIUM = Preferences.getInstance().getDouble("MediumPosition", MEDIUM);
        LOW = Preferences.getInstance().getDouble("LowPosition", LOW);
        FOUR_WHEEL_DRIVE = Preferences.getInstance().getBoolean("4WD", FOUR_WHEEL_DRIVE);
        Logger.log(Logger.Urgency.STATUSREPORT, "Preferences updated");
    }
    private static TheWolf theWolf;
    private final DigitalLimitSwitchModule psiSwitch = new DigitalLimitSwitchModule(new DigitalInput(PSI_SWITCH));
    private final TransferRateCalculator transferRate = new TransferRateCalculator();
    /*
     * Shooter
     */
    private final SpeedControllerModule shooter = new SpeedControllerModule(new Talon(SHOOTER_PORT));
    private final SpeedControllerModule shooterAligner = new SpeedControllerModule(new Victor(SHOOTER_ALIGNMENT_PORT));
    private final PotentiometerModule shooterAngle = new PotentiometerModule(new AnalogChannel(SHOOTER_POSITION));
    private final DigitalLimitSwitchModule shooterSwitch = new DigitalLimitSwitchModule(new DigitalInput(SHOOTER_LIMIT_SWITCH));
    private final HallEffectModule hallEffect = new HallEffectModule(new DigitalInput(HALLEFFECT_PORT));
    private final BangBangModule WOLF_SHOOTER = new BangBangModule(hallEffect, shooter, 0.5);
    private final SpikeRelayModule loader = new SpikeRelayModule(new Relay(LOADER_PORT));
    private final Shooter WOLF_SHOOT = new Shooter(loader, psiSwitch, shooterAngle, shooterSwitch, shooterAligner, WOLF_SHOOTER);
    /*
     * Driving
     */
    private final SpeedControllerModule leftBack = new SpeedControllerModule(new Victor(DRIVE[0])),
            leftFront = new SpeedControllerModule(new Victor(DRIVE[1])),
            rightBack = new SpeedControllerModule(new Victor(DRIVE[2])),
            rightFront = new SpeedControllerModule(new Victor(DRIVE[3]));
    private final RobotDriveModule drive = new RobotDriveModule(new RobotDrive(leftFront, leftBack, rightFront, rightBack), false, true);
    private final EncoderModule leftEncoder = new EncoderModule(new Encoder(ENCODER[0], ENCODER[1]), EncoderModule.DISTANCE);
    private final GyroModule gyro = new GyroModule(new Gyro(GYRO));
    private final SolenoidModule gearUp = new SolenoidModule(new Solenoid(GEAR_UP)),
            gearDown = new SolenoidModule(new Solenoid(GEAR_DOWN));
    private final ShiftingDrivetrain WOLF_DRIVE = new ShiftingDrivetrain(drive, gearDown, gearUp);
    private final PIDModule DRIVETRAIN_PID = new PIDModule(new PIDController(1, 0, 0, leftEncoder, drive));
    /*
     * Alignment
     */
    private final SolenoidModule shortAlignOut = new SolenoidModule(new Solenoid(SHORT_ALIGN_OUT_PORT)),
            shortAlignIn = new SolenoidModule(new Solenoid(SHORT_ALIGN_IN_PORT)),
            longAlignOut = new SolenoidModule(new Solenoid(LONG_ALIGN_OUT_PORT)),
            longAlignIn = new SolenoidModule(new Solenoid(LONG_ALIGN_IN_PORT)),
            staticAlignIn = new SolenoidModule(new Solenoid(STATIC_ALIGN_IN_PORT)),
            staticAlignOut = new SolenoidModule(new Solenoid(STATIC_ALIGN_OUT_PORT));
    private final AlignmentSystem WOLF_ALIGN = new AlignmentSystem(shortAlignOut, shortAlignIn, longAlignOut,
            longAlignIn, staticAlignOut, staticAlignIn);

    /*
     * Joysticks
     */
    private final XboxController WOLF_CONTROL = new XboxController(new Joystick(JOYSTICK_1));
    private final XboxController WOLF_SHOT_CONTROL = new XboxController(new Joystick(JOYSTICK_2));

    /**
     *
     * @return
     */
    public static Robot fetchTheHound() {
        return (theWolf == null) ? (theWolf = new TheWolf()) : (theWolf);
    }

    private TheWolf() {
        Preferences.getInstance().putDouble("ShooterSetpoint", SETPOINT);
        Preferences.getInstance().putDouble("DefaultSpeed", DEFAULTSPEED);
        Preferences.getInstance().putDouble("HighPosition", HIGH);
        Preferences.getInstance().putDouble("MedHighPosition", MED_HIGH);
        Preferences.getInstance().putDouble("MediumPosition", MEDIUM);
        Preferences.getInstance().putDouble("LowPosition", LOW);
        Preferences.getInstance().putBoolean("4WD", FOUR_WHEEL_DRIVE);
        WOLF_SHOOTER.reverse();
        WOLF_SHOOTER.setPastSetpoint(40);
        drive.addFunction(new Function() {
            public double F(double input) {
                return (input * input * input) + 0.25;
            }
        });
    }

    public void robotInit() {
        // Refilling capacity using spike relay always on
        SpikeRelayModule compressor = new SpikeRelayModule(new Relay(COMPRESSOR));
        compressor.enable();
        Logger.log(Logger.Urgency.STATUSREPORT, "Starting compressor...");
        compressor.set(SpikeRelay.FORWARD);
        // Refilling capacity using spike relay always on
    }

    public void disabledInit() {
        updateValues();
        leftBack.disable();
        leftFront.disable();
        rightBack.disable();
        rightFront.disable();
        WOLF_SHOOT.disable();
        WOLF_SHOOTER.disable();
        WOLF_ALIGN.disable();
        WOLF_CONTROL.disable();
        WOLF_SHOT_CONTROL.disable();
        WOLF_DRIVE.disable();
        DRIVETRAIN_PID.disable();
        hallEffect.disable();
        gyro.disable();
        SmartDashboard.putBoolean("PastSetpoint", false);
        SmartDashboard.putBoolean("Enabled", false);
        Logger.log(Logger.Urgency.STATUSREPORT, "Robot fully disabled");
    }

    public void disabledPeriodic() {
    }

    public void autonomousInit() {
        updateValues();
        leftBack.enable();
        leftFront.enable();
        rightBack.enable();
        rightFront.enable();
        WOLF_DRIVE.enable();
        drive.setSafetyEnabled(false);
        WOLF_SHOOT.enable();
        WOLF_SHOOTER.enable();
        WOLF_ALIGN.enable();
        DRIVETRAIN_PID.enable();
        gyro.enable();
        SmartDashboard.putBoolean("Enabled", true);
        Logger.log(Logger.Urgency.STATUSREPORT, "Gordian init...");
        Gordian.ensureInit(WOLF_DRIVE, WOLF_SHOOT, WOLF_SHOOTER, WOLF_ALIGN, DRIVETRAIN_PID);
        try {
            String current = Preferences.getInstance().getString("AutonomousMode", "auto");
            Logger.log(Logger.Urgency.USERMESSAGE, "Running " + current + " autonomous mode.");
            Gordian.run("auto/" + current + ".txt");
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.log(Logger.Urgency.URGENT, "AUTO DID NOT RUN");
        }
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit() {
        updateValues();
        leftBack.enable();
        rightBack.enable();
        if (FOUR_WHEEL_DRIVE) {
            leftFront.enable();
            rightFront.enable();
        }
        WOLF_SHOOT.enable();
        WOLF_SHOOTER.enable();
        WOLF_ALIGN.enable();
        WOLF_CONTROL.enable();
        WOLF_SHOT_CONTROL.enable();
        WOLF_DRIVE.enable();

        SmartDashboard.putBoolean("Enabled", true);
        Logger.log(Logger.Urgency.USERMESSAGE, "Doing binds");
        WOLF_CONTROL.removeAllBinds();
        WOLF_SHOT_CONTROL.removeAllBinds();
        // Driving //
        WOLF_CONTROL.bindAxis(XboxController.RIGHT_FROM_MIDDLE, new SideBinding(drive, SideBinding.RIGHT));
        WOLF_CONTROL.bindAxis(XboxController.LEFT_FROM_MIDDLE, new SideBinding(drive, SideBinding.LEFT));
        WOLF_CONTROL.bindWhenPressed(XboxController.RIGHT_BUMPER, new GearShift(gearUp, gearDown));
        // Alignment //
        WOLF_CONTROL.bindWhenPressed(XboxController.Y, new AlignCommand(WOLF_ALIGN, AlignCommand.COLLAPSE));
        WOLF_CONTROL.bindWhenPressed(XboxController.B, new AlignCommand(WOLF_ALIGN, AlignCommand.LONG));
        WOLF_CONTROL.bindWhenPressed(XboxController.A, new AlignCommand(WOLF_ALIGN, AlignCommand.SHORT));
        // Shooting //
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.RIGHT_BUMPER, new ShootCommand(WOLF_SHOOT, true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.START, new BangBangCommand(WOLF_SHOOTER, SETPOINT));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.BACK, new BangBangCommand(WOLF_SHOOTER, 0));
        WOLF_SHOT_CONTROL.bindWhilePressed(XboxController.RIGHT_STICK, new AutoShoot(WOLF_SHOOT, WOLF_SHOOTER, true));
        WOLF_SHOOTER.setDefaultSpeed(DEFAULTSPEED);
        WOLF_SHOOTER.setSetpoint(0);
        // Shooter position //
        WOLF_SHOT_CONTROL.bindAxis(XboxController.TRIGGERS + XboxController.SHIFT, new AxisBind() {
            public void set(double axisValue) {
                if (!(axisValue < 0 && shooterSwitch.isPushed())) {
                    shooterAligner.set(axisValue);
                } else {
                    shooterAligner.set(0);
                }
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.Y, new ShooterAlignCommand(WOLF_SHOOT, HIGH, true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.X, new ShooterAlignCommand(WOLF_SHOOT, MED_HIGH, true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.B, new ShooterAlignCommand(WOLF_SHOOT, MEDIUM, true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.A, new ShooterAlignCommand(WOLF_SHOOT, LOW, true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.Y + XboxController.SHIFT, new Command() {
            public void run() {
                HIGH = shooterAngle.getPosition();
                Preferences.getInstance().putDouble("HighPosition", HIGH);
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.Y);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.Y, new ShooterAlignCommand(WOLF_SHOOT, HIGH, true));
                Logger.log(Logger.Urgency.USERMESSAGE, "High set to " + HIGH);
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.X + XboxController.SHIFT, new Command() {
            public void run() {
                MED_HIGH = shooterAngle.getPosition();
                Preferences.getInstance().putDouble("MedHighPosition", MED_HIGH);
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.X);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.X, new ShooterAlignCommand(WOLF_SHOOT, MED_HIGH, true));
                Logger.log(Logger.Urgency.USERMESSAGE, "MedHigh set to " + MED_HIGH);
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.B + XboxController.SHIFT, new Command() {
            public void run() {
                MEDIUM = shooterAngle.getPosition();
                Preferences.getInstance().putDouble("MediumPosition", MEDIUM);
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.B);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.B, new ShooterAlignCommand(WOLF_SHOOT, MEDIUM, true));
                Logger.log(Logger.Urgency.USERMESSAGE, "Medium set to " + MEDIUM);
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.A + XboxController.SHIFT, new Command() {
            public void run() {
                LOW = shooterAngle.getPosition();
                Preferences.getInstance().putDouble("LowPosition", LOW);
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.A);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.A, new ShooterAlignCommand(WOLF_SHOOT, LOW, true));
                Logger.log(Logger.Urgency.USERMESSAGE, "Low set to " + LOW);
            }
        });
        Logger.log(Logger.Urgency.USERMESSAGE, "Teleop ready");

        buf = 0;
        avg = 0;
    }
    double buf;
    double avg;

    public void teleopPeriodic() {
        WOLF_CONTROL.doBinds();
        WOLF_SHOT_CONTROL.doBinds();
        final double rate = hallEffect.getRate();
        SmartDashboard.putBoolean("PastSetpoint", WOLF_SHOOTER.pastSetpoint());
        SmartDashboard.putNumber("HallEffectRate", rate);
        SmartDashboard.putNumber("ShooterRPM", rate);
        SmartDashboard.putNumber("ShooterPosition", shooterAngle.getPosition());
        SmartDashboard.putNumber("NetworkLag", transferRate.packetsPerMillisecond());
        SmartDashboard.putBoolean("60 PSI", psiSwitch.isPushed());

        buf = Math.abs(SETPOINT - rate);
        avg = (buf * 0.00321 + avg * 0.99679);
        SmartDashboard.putNumber("Average Offset", avg);
    }

    public void testInit() {
        updateValues();
        hallEffect.enable();
    }

    public void testPeriodic() {
    }
}
