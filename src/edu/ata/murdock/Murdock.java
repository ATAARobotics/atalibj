package edu.ata.murdock;

import edu.ata.autonomous.GordianAuto;
import edu.ata.commands.AlignCommand;
import edu.ata.commands.AlignShooter;
import edu.ata.commands.AutoShoot;
import edu.ata.commands.BangBangCommand;
import edu.ata.commands.ChangeRPMCommand;
import edu.ata.commands.GearShiftCommand;
import edu.ata.commands.ShootCommand;
import edu.ata.commands.SwitchBitchBar;
import edu.ata.modules.AlignmentMotor;
import edu.ata.modules.XboxController;
import edu.ata.preferences.RPMPreference;
import edu.ata.subsystems.AlignmentSystem;
import edu.ata.subsystems.GearShifters;
import edu.ata.subsystems.ReversingSolenoids;
import edu.ata.subsystems.Shooter;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.driving.ArcadeBinding;
import edu.first.module.driving.Function;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.sensor.DigitalLimitSwitchModule;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.sensor.GyroModule;
import edu.first.module.sensor.HallEffectModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.speedcontroller.SpeedControllerBinding;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.speedcontroller.SpikeRelayModule;
import edu.first.module.target.BangBangModule;
import edu.first.module.target.MovingModule;
import edu.first.robot.Robot;
import edu.first.robot.RobotAdapter;
import edu.first.utils.DriverstationInfo;
import edu.first.utils.Logger;
import edu.first.utils.TransferRateCalculator;
import edu.first.utils.preferences.DoublePreference;
import edu.first.utils.preferences.StringPreference;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Our 2013 robot, Murdock. Our beginning and end.
 *
 * @author Joel Gallant
 */
public final class Murdock {

    // Preferences in code //
    private static final double defaultSetpoint = 4000;
    private static final double shooterRPMTolerance = 40;
    private static final double shiftersInputThreshhold = 0.7;
    private static final double shiftersSpeedThreshhold = 10000;
    private static final double shiftersShiftTimeLimit = 0.5;
    private static final double dP = 0.001, dI = 0, dD = 0.001;
    private static final double tP = 1, tI = 0, tD = 0;
    private static final double drivetrainDistanceTolerance = 40;
    private static final double drivetrainTurningTolerance = 3;
    private static final double drivetrainPIDMaxSpeed = 0.7;
    private static final double drivetrainPIDMinSpeed = 0.3;
    private static final double drivetrainPIDMaxTurn = 0.8;
    private static final double drivetrainPIDMinTurn = 0.5;
    private static final double shooterRPMSpeedChange = 20;
    private static final double shooterRPMBigSpeedChange = 1000;
    private static final double defaultA = 7;
    private static final double defaultB = 6;
    private static final double defaultX = 5;
    private static final double defaultY = 4;
    private static final String defaultAuto = "auto";
    private static final boolean reverseSpeed = false;
    private static final boolean reverseTurn = true;
    private static final boolean reverseShooter = false;
    private static final boolean reverseAlignmentTriggers = false;
    private static final int competitionPort = 1;
    private static final int smartDashboardPort = 2;
    private static final Function DRIVER_FUNCTION = new Function() {
        public double F(double input) {
            return input != 0 ? ((input * input * input) + 0.12) : 0;
        }
    };
    private static final Function SHIFTER_FUNCTION = new Function() {
        private final Murdock m = getInstance();
        private long lastShift = 0;

        public double F(double input) {
//            if (input > shiftersInputThreshhold && m.encoder.getRate() > shiftersSpeedThreshhold 
//                    // Make sure not to shift too much
//                    && (System.currentTimeMillis() - lastShift) > shiftersShiftTimeLimit * 1000) {
//                m.gearShifterController.setSecondGear();
//                lastShift = System.currentTimeMillis();
//            } else {
//                m.gearShifterController.setFirstGear();
//            }
            return input;
        }
    };
    // Important things //
    private static Murdock MURDOCK;
    private final Robot murdock = new MurdockRobot();
    private final RobotMode fullTestingMode = new FullTestingMode();
    private final RobotMode competitionMode = new CompetitionMode();
    private long lastSave = System.currentTimeMillis();
    private RPMPreference RPM = new RPMPreference("Shooter", defaultSetpoint);
    private StringPreference AUTOMODE = new StringPreference("AutonomousMode", defaultAuto);
    private DoublePreference ASetpoint = new DoublePreference("ASetpoint", defaultA);
    private DoublePreference BSetpoint = new DoublePreference("BSetpoint", defaultB);
    private DoublePreference XSetpoint = new DoublePreference("XSetpoint", defaultX);
    private DoublePreference YSetpoint = new DoublePreference("YSetpoint", defaultY);
    // WPILIBJ //
    private final DigitalInput _psiSwitch = new DigitalInput(PortMapFile.getInstance().getPort("PSISwitch", 5));
    private final AnalogChannel _potentiometer = new AnalogChannel(PortMapFile.getInstance().getPort("Pot", 1));
    private final DigitalInput _hallEffect = new DigitalInput(PortMapFile.getInstance().getPort("HallEffect", 1));
    private final Encoder _encoder = new Encoder(PortMapFile.getInstance().getPort("EncoderA", 2),
            PortMapFile.getInstance().getPort("EncoderB", 3));
    private final Gyro _gyro = new Gyro(PortMapFile.getInstance().getPort("Gyro", 2));
    private final Relay _compressor = new Relay(PortMapFile.getInstance().getPort("Compressor", 1));
    private final Joystick _joystick1 = new Joystick(PortMapFile.getInstance().getPort("Joystick1", 1));
    private final Joystick _joystick2 = new Joystick(PortMapFile.getInstance().getPort("Joystick2", 2));
    private final Talon _shooter = new Talon(PortMapFile.getInstance().getPort("Shooter", 1));
    private final Victor _shooterAligner = new Victor(PortMapFile.getInstance().getPort("ShooterAlignment", 2));
    private final Victor _leftBack = new Victor(PortMapFile.getInstance().getPort("LeftBack", 5));
    private final Victor _leftFront = new Victor(PortMapFile.getInstance().getPort("LeftFront", 6));
    private final Victor _rightBack = new Victor(PortMapFile.getInstance().getPort("RightBack", 3));
    private final Victor _rightFront = new Victor(PortMapFile.getInstance().getPort("RightFront", 4));
    private final RobotDrive _drive = new RobotDrive(_leftFront, _leftBack, _rightFront, _rightBack);
    private final Solenoid _loadOut = new Solenoid(PortMapFile.getInstance().getPort("LoadOut", 7));
    private final Solenoid _gearUp = new Solenoid(PortMapFile.getInstance().getPort("GearUp", 3));
    private final Solenoid _gearDown = new Solenoid(PortMapFile.getInstance().getPort("GearDown", 4));
    private final Solenoid _bitchBarIn = new Solenoid(PortMapFile.getInstance().getPort("BitchBarIn", 5));
    private final Solenoid _bitchBarOut = new Solenoid(PortMapFile.getInstance().getPort("BitchBarOut", 6));
    private final Solenoid _backLeft = new Solenoid(PortMapFile.getInstance().getPort("BackLeft", 1));
    private final Solenoid _backRight = new Solenoid(PortMapFile.getInstance().getPort("BackRight", 2));
    // Robot //
    private final TransferRateCalculator transferRate = new TransferRateCalculator();
    private final DigitalLimitSwitchModule psiSwitch = new DigitalLimitSwitchModule(_psiSwitch);
    private final PotentiometerModule potentiometer = new PotentiometerModule(_potentiometer);
    private final HallEffectModule hallEffect = new HallEffectModule(_hallEffect);
    private final EncoderModule encoder = new EncoderModule(_encoder, Encoder.PIDSourceParameter.kDistance);
    private final GyroModule gyro = new GyroModule(_gyro);
    private final SpikeRelayModule compressor = new SpikeRelayModule(_compressor);
    private final XboxController joystick1 = new XboxController(_joystick1);
    private final XboxController joystick2 = new XboxController(_joystick2);
    private final SpeedControllerModule shooter = new SpeedControllerModule(_shooter);
    private final SpeedControllerModule shooterAligner = new SpeedControllerModule(_shooterAligner);
    private final RobotDriveModule drive = new RobotDriveModule(_drive, reverseSpeed, reverseTurn);
    private final SolenoidModule loadOut = new SolenoidModule(_loadOut);
    private final SolenoidModule gearUp = new SolenoidModule(_gearUp);
    private final SolenoidModule gearDown = new SolenoidModule(_gearDown);
    private final SolenoidModule bitchBarIn = new SolenoidModule(_bitchBarIn);
    private final SolenoidModule bitchBarOut = new SolenoidModule(_bitchBarOut);
    private final SolenoidModule backLeft = new SolenoidModule(_backLeft);
    private final SolenoidModule backRight = new SolenoidModule(_backRight);
    // Subsystems //
    private final BangBangModule shooterController =
            new BangBangModule(hallEffect, shooter, RPM.getDefaultSpeed(), shooterRPMTolerance, reverseShooter);
    private final AlignmentMotor alignmentMotor = new AlignmentMotor(shooterAligner);
    private final Shooter shotController =
            new Shooter(loadOut, potentiometer, alignmentMotor, shooterController);
    private final GearShifters gearShifterController =
            new GearShifters(gearDown, gearUp);
    private final ReversingSolenoids bitchBar =
            new ReversingSolenoids(bitchBarIn, bitchBarOut);
    private final AlignmentSystem alignment =
            new AlignmentSystem(backLeft, backRight);
    private final MovingModule drivetrainController =
            new MovingModule(encoder, gyro, drive, dP, dI, dD, tP, tI, tD,
            drivetrainDistanceTolerance, drivetrainTurningTolerance,
            drivetrainPIDMaxSpeed, drivetrainPIDMinSpeed, drivetrainPIDMaxTurn, drivetrainPIDMinTurn);

    /**
     *
     * @return
     */
    public static Murdock getInstance() {
        synchronized (Murdock.class) {
            if (MURDOCK == null) {
                MURDOCK = new Murdock();
            }
        }
        return MURDOCK;
    }

    private Murdock() {
    }

    /**
     *
     * @return
     */
    public Robot getRobot() {
        return murdock;
    }

    private RobotMode getSelectedRobot() {
        if (DriverstationInfo.getDS().getDigitalIn(competitionPort)) {
            return competitionMode;
        } else {
            return fullTestingMode;
        }
    }

    private void init() {
        Logger.log(Logger.Urgency.USERMESSAGE, "IO " + competitionPort + " = Competition");
        Logger.log(Logger.Urgency.USERMESSAGE, "IO " + smartDashboardPort + " = SmartDashboard");
        if (DriverstationInfo.FMSattached()) {
            Logger.log(Logger.Urgency.USERMESSAGE, "FMS attached - reverting to competition mode");
            DriverstationInfo.getDS().setDigitalOut(1, true);
        }
        RPM.create();
        AUTOMODE.create();
        ASetpoint.create();
        BSetpoint.create();
        XSetpoint.create();
        YSetpoint.create();

        compressor.enable();
        compressor.set(Relay.Value.kForward);

        drive.addFunction(DRIVER_FUNCTION);
        drive.addFunction(SHIFTER_FUNCTION);
    }

    private void disabled() {
        // can only save every 30 seconds - prevents breakage
        if (System.currentTimeMillis() - lastSave > 30000) {
            lastSave = System.currentTimeMillis();
            Logger.log(Logger.Urgency.USERMESSAGE, "Saving Preferences");
            Preferences.getInstance().save();
        }
        psiSwitch.disable();
        potentiometer.disable();
        hallEffect.disable();
        encoder.disable();
        gyro.disable();
        joystick1.disable();
        joystick2.disable();
        shooter.disable();
        shooterAligner.disable();
        drive.disable();
        loadOut.disable();
        gearUp.disable();
        gearDown.disable();
        bitchBarIn.disable();
        bitchBarOut.disable();
        backLeft.disable();
        backRight.disable();
        shooterController.disable();
        shotController.disable();
        gearShifterController.disable();
        bitchBar.disable();
        alignment.disable();
        drivetrainController.disable();

        Logger.log(Logger.Urgency.USERMESSAGE, "Robot is disabled.");
    }

    private void doScriptAutonomous() {
        gearShifterController.enable();
        drive.enable();
        shotController.enable();
        shooterController.enable();
        alignment.enable();
        encoder.enable();
        gyro.enable();

        drive.setSafetyEnabled(false);
        shooterController.setDefaultSpeed(0);
        encoder.reset();

        GordianAuto.ensureInit(gearShifterController, drive, shotController,
                shooterController, alignment, drivetrainController, encoder, gyro);
        try {
            String current = AUTOMODE.get();
            Logger.log(Logger.Urgency.USERMESSAGE, "Running auto/" + current + ".txt");
            GordianAuto.run("auto/" + current + ".txt");
            Logger.log(Logger.Urgency.USERMESSAGE, "Autonomous complete");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.log(Logger.Urgency.USERMESSAGE, "AUTO DID NOT RUN");
        }
    }

    private void doTeleopBinds() {
        joystick1.removeAllBinds();
        joystick2.removeAllBinds();
        // Driving
        joystick1.bindAxis(XboxController.LEFT_FROM_MIDDLE,
                new ArcadeBinding(drive, ArcadeBinding.FORWARD));
        joystick1.bindAxis(XboxController.RIGHT_X,
                new ArcadeBinding(drive, ArcadeBinding.ROTATE));
        joystick1.bindWhenPressed(XboxController.RIGHT_STICK,
                new AlignCommand(alignment, AlignCommand.REVERSE, false));
        joystick1.bindWhenPressed(XboxController.LEFT_STICK,
                new AlignCommand(alignment, AlignCommand.RIGHT, false));
        joystick1.bindWhenPressed(XboxController.RIGHT_BUMPER,
                new GearShiftCommand(gearShifterController, GearShiftCommand.SECOND, false));
        joystick1.bindWhenPressed(XboxController.LEFT_BUMPER,
                new GearShiftCommand(gearShifterController, GearShiftCommand.FIRST, false));
        joystick1.bindWhenPressed(XboxController.A,
                new AlignShooter(shotController, ASetpoint, true));
        joystick1.bindWhenPressed(XboxController.B,
                new AlignShooter(shotController, BSetpoint, true));
        joystick1.bindWhenPressed(XboxController.X,
                new AlignShooter(shotController, XSetpoint, true));
        joystick1.bindWhenPressed(XboxController.Y,
                new AlignShooter(shotController, YSetpoint, true));
        joystick1.bindAxis(XboxController.TRIGGERS,
                new SpeedControllerBinding(alignmentMotor, reverseAlignmentTriggers));
        // Shooting
        joystick2.bindWhenPressed(XboxController.RIGHT_BUMPER,
                new ShootCommand(shotController, true));
        joystick2.bindWhilePressed(XboxController.RIGHT_STICK,
                new AutoShoot(shotController, shooterController, true));
        joystick2.bindWhenPressed(XboxController.BACK,
                new BangBangCommand(shooterController, 0, false));
        joystick2.bindWhenPressed(XboxController.START,
                new BangBangCommand(shooterController, RPM, false));
        joystick2.bindWhenPressed(XboxController.LEFT_STICK,
                new SwitchBitchBar(bitchBar, false));
        joystick2.bindWhenPressed(XboxController.B,
                new ChangeRPMCommand(RPM, +shooterRPMSpeedChange, shooterController, false));
        joystick2.bindWhenPressed(XboxController.A,
                new ChangeRPMCommand(RPM, -shooterRPMSpeedChange, shooterController, false));
        joystick2.bindWhenPressed(XboxController.Y,
                new ChangeRPMCommand(RPM, +shooterRPMBigSpeedChange, shooterController, false));
        joystick2.bindWhenPressed(XboxController.X,
                new ChangeRPMCommand(RPM, -shooterRPMBigSpeedChange, shooterController, false));

        Logger.log(Logger.Urgency.USERMESSAGE, "Teleop Binds Ready");
    }

    private boolean isSmartDashboard() {
        return DriverstationInfo.getDS().getDigitalIn(smartDashboardPort);
    }

    public final class FullTestingMode extends RobotMode {

        public void autonomousInit() {
            doScriptAutonomous();
        }

        public void teleopInit() {
            joystick1.enable();
            joystick2.enable();
            psiSwitch.enable();
            encoder.enable();
            gyro.enable();
            bitchBar.enable();
            alignment.enable();
            gearShifterController.enable();
            drive.enable();
            shooterController.enable();
            shotController.enable();

            shooterController.setSetpoint(RPM.getRPM());
            shooterController.setDefaultSpeed(RPM.getDefaultSpeed());
            shooterController.setSetpoint(0);
            drive.setSafetyEnabled(true);

            doTeleopBinds();
        }

        public void teleopPeriodic() {
            joystick1.doBinds();
            joystick2.doBinds();
            if (isSmartDashboard()) {
                SmartDashboard.putBoolean("PastSetpoint", shooterController.pastSetpoint());
                SmartDashboard.putBoolean("60 PSI", !psiSwitch.isPushed());
                SmartDashboard.putBoolean("BBOut", !bitchBar.isIn());
                SmartDashboard.putBoolean("AlignOut", alignment.isOut());
                SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
                SmartDashboard.putNumber("Distance", encoder.getDistance());
                SmartDashboard.putNumber("Angle", gyro.getAngle());
                SmartDashboard.putNumber("ShooterPosition", potentiometer.getPosition());
                SmartDashboard.putNumber("NetworkLag", transferRate.packetsPerMillisecond());
                SmartDashboard.putNumber("Gear", gearShifterController.gear());
            }
        }
    }

    public final class CompetitionMode extends RobotMode {

        private int counter = 0;

        public void autonomousInit() {
            doScriptAutonomous();
        }

        public void teleopInit() {
            joystick1.enable();
            joystick2.enable();
            bitchBar.enable();
            alignment.enable();
            gearShifterController.enable();
            drive.enable();
            shooterController.enable();
            shotController.enable();

            shooterController.setSetpoint(RPM.getRPM());
            shooterController.setDefaultSpeed(RPM.getDefaultSpeed());
            shooterController.setSetpoint(0);
            drive.setSafetyEnabled(true);

            doTeleopBinds();
        }

        public void teleopPeriodic() {
            joystick1.doBinds();
            joystick2.doBinds();
            // Runs every 5 loops (100ms = 0.1 secs)
            if (++counter > 5 && isSmartDashboard()) {
                counter = 0;
                SmartDashboard.putBoolean("PastSetpoint", shooterController.pastSetpoint());
                SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
                SmartDashboard.putNumber("ShooterPosition", potentiometer.getPosition());
                SmartDashboard.putNumber("Gear", gearShifterController.gear());
            }
        }
    }

    // Meant to restrict access to robotInit() and disabledInit()
    private static class RobotMode extends RobotAdapter {

        public final void robotInit() {
        }

        public final void disabledInit() {
        }
    }

    private final class MurdockRobot extends RobotAdapter {

        public void robotInit() {
            init();
        }

        public void disabledInit() {
            disabled();
        }
        private Robot robot = getSelectedRobot();

        public void autonomousInit() {
            (robot = getSelectedRobot()).autonomousInit();
        }

        public void autonomousPeriodic() {
            robot.autonomousPeriodic();
        }

        public void teleopInit() {
            (robot = getSelectedRobot()).teleopInit();
        }

        public void teleopPeriodic() {
            robot.teleopPeriodic();
        }

        public void testInit() {
            (robot = getSelectedRobot()).testInit();
        }

        public void testPeriodic() {
            robot.testPeriodic();
        }
    }
}
