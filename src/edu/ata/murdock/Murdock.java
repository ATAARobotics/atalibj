package edu.ata.murdock;

import edu.ata.autonomous.GordianAuto;
import edu.ata.commands.AlignCommand;
import edu.ata.commands.AutoShoot;
import edu.ata.commands.BangBangCommand;
import edu.ata.commands.GearShiftCommand;
import edu.ata.commands.ShootCommand;
import edu.ata.commands.ShooterAlignCommand;
import edu.ata.commands.SwitchBitchBar;
import edu.ata.modules.XboxController;
import edu.ata.subsystems.AlignmentSystem;
import edu.ata.subsystems.GearShifters;
import edu.ata.subsystems.ReversingSolenoids;
import edu.ata.subsystems.Shooter;
import edu.first.bindings.AxisBind;
import edu.first.command.Command;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.driving.ArcadeBinding;
import edu.first.module.driving.Function;
import edu.first.module.driving.RobotDriveModule;
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
import edu.first.robot.RobotAdapter;
import edu.first.utils.Logger;
import edu.first.utils.TransferRateCalculator;
import edu.first.utils.preferences.BooleanPreference;
import edu.first.utils.preferences.DoublePreference;
import edu.first.utils.preferences.StringPreference;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Our 2013 robot, Murdock. Our beginning and end.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class Murdock extends RobotAdapter implements PortMap {

    private Murdock() {
    }

    public void robotInit() {
        // Refilling capacity using spike relay always on
        SpikeRelayModule compressor = new SpikeRelayModule(new Relay(COMPRESSOR));
        compressor.enable();
        Logger.log(Logger.Urgency.STATUSREPORT, "Starting compressor...");
        compressor.set(SpikeRelay.FORWARD);
        // Refilling capacity using spike relay always on
        SETPOINT.create();
        DEFAULTSPEED.create();
        Y.create();
        X.create();
        B.create();
        A.create();
        FOUR_WHEEL_DRIVE.create();
        AUTOMODE.create();

        SmartDashboard.putData("PID", drivetrainPID);
        WOLF_SHOOTER.reverse();
        WOLF_SHOOTER.setPastSetpoint(40);
        drive.addFunction(new Function() {
            public double F(double input) {
                if (input != 0) {
                    return (input * input * input) + 0.12;
                } else {
                    return 0;
                }
            }
        });
        drive.setCompensation(0);
        DRIVETRAIN_PID.setTolerance(40);
        DRIVETRAIN_PID.setOutputRange(-0.3, 0.3);
    }

    public void disabledInit() {
        leftBack.disable();
        leftFront.disable();
        rightBack.disable();
        rightFront.disable();
        WOLF_SHOOT.disable();
        WOLF_SHOOTER.disable();
        WOLF_ALIGN.disable();
        WOLF_CONTROL.disable();
        WOLF_SHOT_CONTROL.disable();
        shifters.disable();
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
        leftBack.enable();
        rightBack.enable();
        if (FOUR_WHEEL_DRIVE.get()) {
            leftFront.enable();
            rightFront.enable();
        }
        shifters.enable();
        drive.enable();
        drive.setSafetyEnabled(false);
        WOLF_SHOOT.enable();
        WOLF_SHOOTER.enable();
        WOLF_SHOOTER.setDefaultSpeed(0);
        WOLF_ALIGN.enable();
        leftEncoder.enable();
        leftEncoder.reset();
        gyro.enable();
        SmartDashboard.putBoolean("Enabled", true);
        Logger.log(Logger.Urgency.STATUSREPORT, "Gordian init...");
        GordianAuto.ensureInit(shifters, drive, WOLF_SHOOT, WOLF_SHOOTER, WOLF_ALIGN, DRIVETRAIN_PID, leftEncoder, gyro);
        try {
            String current = AUTOMODE.get();
            Logger.log(Logger.Urgency.USERMESSAGE, "Running " + current + ".txt");
            GordianAuto.run("auto/" + current + ".txt");
            Logger.log(Logger.Urgency.USERMESSAGE, "Autonomous complete");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.log(Logger.Urgency.URGENT, "AUTO DID NOT RUN");
        }
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit() {
        leftBack.enable();
        rightBack.enable();
        if (FOUR_WHEEL_DRIVE.get()) {
            leftFront.enable();
            rightFront.enable();
        }
        leftEncoder.enable();
        leftEncoder.reset();
        gyro.enable();
        BITCH_BAR.enable();
        WOLF_SHOOT.enable();
        WOLF_SHOOTER.enable();
        WOLF_ALIGN.enable();
        WOLF_CONTROL.enable();
        WOLF_SHOT_CONTROL.enable();
        shifters.enable();
        drive.setSafetyEnabled(true);
        drive.setMaxOutput(1);

        SmartDashboard.putBoolean("Enabled", true);
        Logger.log(Logger.Urgency.USERMESSAGE, "Doing binds");
        WOLF_CONTROL.removeAllBinds();
        WOLF_SHOT_CONTROL.removeAllBinds();
        // Driving //
        WOLF_CONTROL.bindAxis(XboxController.LEFT_FROM_MIDDLE, new ArcadeBinding(drive, ArcadeBinding.FORWARD));
        WOLF_CONTROL.bindAxis(XboxController.RIGHT_X, new ArcadeBinding(drive, ArcadeBinding.ROTATE));
        WOLF_CONTROL.bindWhenPressed(XboxController.RIGHT_BUMPER, new GearShiftCommand(shifters, true));
        // Alignment //
        WOLF_CONTROL.bindWhenPressed(XboxController.X, new SwitchBitchBar(BITCH_BAR, true));
        WOLF_CONTROL.bindWhenPressed(XboxController.B, new AlignCommand(WOLF_ALIGN, AlignCommand.COLLAPSE, true));
        WOLF_CONTROL.bindWhenPressed(XboxController.A, new AlignCommand(WOLF_ALIGN, AlignCommand.EXTEND, true));
        // Shooting //
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.RIGHT_BUMPER, new ShootCommand(WOLF_SHOOT, true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.START, new BangBangCommand(WOLF_SHOOTER, SETPOINT.get(), false));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.BACK, new BangBangCommand(WOLF_SHOOTER, 0, false));
        WOLF_SHOT_CONTROL.bindWhilePressed(XboxController.RIGHT_STICK, new AutoShoot(WOLF_SHOOT, WOLF_SHOOTER, true));
        WOLF_SHOOTER.setDefaultSpeed(DEFAULTSPEED.get());
        WOLF_SHOOTER.setSetpoint(0);
        // Shooter position //
        WOLF_SHOT_CONTROL.bindAxis(XboxController.TRIGGERS + XboxController.SHIFT, new AxisBind() {
            public void set(double axisValue) {
                shooterAligner.set(axisValue);
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.Y, new ShooterAlignCommand(WOLF_SHOOT, Y.get(), true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.X, new ShooterAlignCommand(WOLF_SHOOT, X.get(), true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.B, new ShooterAlignCommand(WOLF_SHOOT, B.get(), true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.A, new ShooterAlignCommand(WOLF_SHOOT, A.get(), true));
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.Y + XboxController.SHIFT, new Command() {
            public void run() {
                Y.set(shooterAngle.getPosition());
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.Y);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.Y, new ShooterAlignCommand(WOLF_SHOOT, Y.get(), true));
                Logger.log(Logger.Urgency.USERMESSAGE, "High set to " + Y);
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.X + XboxController.SHIFT, new Command() {
            public void run() {
                X.set(shooterAngle.getPosition());
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.X);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.X, new ShooterAlignCommand(WOLF_SHOOT, X.get(), true));
                Logger.log(Logger.Urgency.USERMESSAGE, "MedHigh set to " + X);
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.B + XboxController.SHIFT, new Command() {
            public void run() {
                B.set(shooterAngle.getPosition());
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.B);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.B, new ShooterAlignCommand(WOLF_SHOOT, B.get(), true));
                Logger.log(Logger.Urgency.USERMESSAGE, "Medium set to " + B);
            }
        });
        WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.A + XboxController.SHIFT, new Command() {
            public void run() {
                A.set(shooterAngle.getPosition());
                WOLF_SHOT_CONTROL.removeButtonBinds(XboxController.A);
                WOLF_SHOT_CONTROL.bindWhenPressed(XboxController.A, new ShooterAlignCommand(WOLF_SHOOT, A.get(), true));
                Logger.log(Logger.Urgency.USERMESSAGE, "Low set to " + A);
            }
        });
        Logger.log(Logger.Urgency.USERMESSAGE, "Teleop ready");

        buf = 0;
        avg = 0;
        setpoint = SETPOINT.get();
    }
    double buf;
    double avg;
    double setpoint;

    public void teleopPeriodic() {
        WOLF_CONTROL.doBinds();
        WOLF_SHOT_CONTROL.doBinds();
        final double rate = hallEffect.getRate();
        SmartDashboard.putBoolean("PastSetpoint", WOLF_SHOOTER.pastSetpoint());
        SmartDashboard.putNumber("HallEffectRate", rate);
        SmartDashboard.putNumber("ShooterRPM", rate);
        SmartDashboard.putNumber("Distance", leftEncoder.getDistance());
        SmartDashboard.putNumber("Angle", gyro.getAngle());
        SmartDashboard.putNumber("ShooterPosition", shooterAngle.getPosition());
        SmartDashboard.putNumber("NetworkLag", transferRate.packetsPerMillisecond());
        SmartDashboard.putBoolean("60 PSI", !psiSwitch.isPushed());

        buf = Math.abs(setpoint - rate);
        avg = (buf * 0.00321 + avg * 0.99679);
        SmartDashboard.putNumber("Average Offset", avg);
    }

    public void testInit() {
    }

    public void testPeriodic() {
    }
    private final static Murdock MURDOCK = new Murdock();

    public static Murdock getInstance() {
        return MURDOCK;
    }
    private static DoublePreference SETPOINT = new DoublePreference("ShooterSetpoint", 4250);
    private static DoublePreference DEFAULTSPEED = new DoublePreference("DefaultSpeed", 0.5);
    private static DoublePreference Y = new DoublePreference("YPosition", 8);
    private static DoublePreference X = new DoublePreference("XPosition", 6);
    private static DoublePreference B = new DoublePreference("BPosition", 4);
    private static DoublePreference A = new DoublePreference("APosition", 2);
    private static BooleanPreference FOUR_WHEEL_DRIVE = new BooleanPreference("4WD", true);
    private static StringPreference AUTOMODE = new StringPreference("AutonomousMode", "auto");
    /*
     * Miscellaneous.
     */
    private final DigitalLimitSwitchModule psiSwitch = new DigitalLimitSwitchModule(new DigitalInput(PSI_SWITCH));
    private final TransferRateCalculator transferRate = new TransferRateCalculator();
    /*
     * Shooter
     */
    private final SpeedControllerModule shooter = new SpeedControllerModule(new Talon(SHOOTER_PORT));
    private final SpeedControllerModule shooterAligner = new SpeedControllerModule(new Victor(SHOOTER_ALIGNMENT_PORT));
    private final PotentiometerModule shooterAngle = new PotentiometerModule(new AnalogChannel(SHOOTER_POSITION));
    private final HallEffectModule hallEffect = new HallEffectModule(new DigitalInput(HALLEFFECT_PORT));
    private final BangBangModule WOLF_SHOOTER = new BangBangModule(hallEffect, shooter, 0.5);
    private final SolenoidModule loadOut = new SolenoidModule(new Solenoid(LOAD_OUT));
    private final Shooter WOLF_SHOOT = new Shooter(loadOut, psiSwitch, shooterAngle, shooterAligner, WOLF_SHOOTER);
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
    private final SolenoidModule gearTwo = new SolenoidModule(new Solenoid(GEAR_UP)),
            gearOne = new SolenoidModule(new Solenoid(GEAR_DOWN));
    private final GearShifters shifters = new GearShifters(gearOne, gearTwo);
    private final PIDController drivetrainPID = new PIDController(0.001, 0, 0.001, leftEncoder, drive);
    private final PIDModule DRIVETRAIN_PID = new PIDModule(drivetrainPID);
    /*
     * Alignment
     */
    private final ReversingSolenoids BITCH_BAR = new ReversingSolenoids(new SolenoidModule(new Solenoid(BITCH_BAR_IN_PORT)),
            new SolenoidModule(new Solenoid(BITCH_BAR_OUT_PORT)));
    private final SolenoidModule alignIn = new SolenoidModule(new Solenoid(BACK_IN)),
            alignOut = new SolenoidModule(new Solenoid(BACK_OUT));
    private final AlignmentSystem WOLF_ALIGN = new AlignmentSystem(alignIn, alignOut);
    /*
     * Joysticks
     */
    private final XboxController WOLF_CONTROL = new XboxController(new Joystick(JOYSTICK_1));
    private final XboxController WOLF_SHOT_CONTROL = new XboxController(new Joystick(JOYSTICK_2));
}