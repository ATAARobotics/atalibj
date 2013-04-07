package edu.ata.murdock;

import edu.ata.autonomous.GordianAuto;
import edu.ata.commands.AlignCommand;
import edu.ata.commands.AlignShooter;
import edu.ata.commands.AutoShoot;
import edu.ata.commands.BangBangCommand;
import edu.ata.commands.ChangeRPMCommand;
import edu.ata.commands.GearShiftCommand;
import edu.ata.commands.ReversingSolenoidsCommand;
import edu.ata.commands.SetFrisbeePusher;
import edu.ata.commands.SetRPMCommand;
import edu.ata.commands.ShootCommand;
import edu.ata.commands.SwitchBitchBar;
import edu.ata.modules.AlignmentMotor;
import edu.ata.modules.XboxController;
import edu.ata.preferences.RPMPreference;
import edu.ata.subsystems.AlignmentSystem;
import edu.ata.subsystems.GearShifters;
import edu.ata.subsystems.ReversingSolenoids;
import edu.ata.subsystems.Shooter;
import edu.ata.subsystems.VexIntegratedMotorEncoder;
import edu.first.bindings.Bindable;
import edu.first.command.Command;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.driving.ArcadeBinding;
import edu.first.module.driving.Function;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.driving.SideBinding;
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
import edu.wpi.first.wpilibj.SpeedController;
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
    private static final double shooterRPMTolerance = 20;
    private static final double shiftersInputThreshhold = 0.7;
    private static final double shiftersSpeedThreshhold = 10000;
    private static final double shiftersShiftTimeLimit = 0.5;
    private static final double dP = 0.001, dI = 0, dD = 0.001;
    private static final double tP = 0.007, tI = 0, tD = 0.001;
    private static final double drivetrainDistanceTolerance = 40;
    private static final double drivetrainTurningTolerance = 0;
    private static final double drivetrainPIDMaxSpeed = 0.7;
    private static final double drivetrainPIDMinSpeed = 0.3;
    private static final double drivetrainPIDMaxTurn = 0.4;
    private static final double drivetrainPIDMinTurn = 0.3;
    private static final double fineAdjustmentCoefficient = 0.5;
    private static final double triggerShotThreashold = 0.7;
    private static final double shooterRPMSpeedChange = 25;
    private static final double defaultArm = 5;
    private static final double defaultRPM = 4000;
    private static final String defaultAuto = "auto";
    private static final boolean reverseSpeed = false;
    private static final boolean reverseTurn = true;
    private static final boolean reverseShooter = false;
    public static final int competitionPort = 1;
    public static final int smartDashboardPort = 2;
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
    private DoublePreference ASetpoint = new DoublePreference("ASetpoint", defaultArm);
    private DoublePreference ARPM = new DoublePreference("ARPM", defaultRPM);
    private DoublePreference BSetpoint = new DoublePreference("BSetpoint", defaultArm);
    private DoublePreference BRPM = new DoublePreference("BRPM", defaultRPM);
    private DoublePreference XSetpoint = new DoublePreference("XSetpoint", defaultArm);
    private DoublePreference XRPM = new DoublePreference("XRPM", defaultRPM);
    private DoublePreference YSetpoint = new DoublePreference("YSetpoint", defaultArm);
    private DoublePreference YRPM = new DoublePreference("YRPM", defaultRPM);
    private DoublePreference BackSetpoint = new DoublePreference("BackSetpoint", defaultArm);
    // WPILIBJ //
    private final DigitalInput _psi120 = new DigitalInput(PortMapFile.getInstance().getPort("psi120", 5));
    private final DigitalInput _psi60 = new DigitalInput(PortMapFile.getInstance().getPort("psi60", 6));
    private final AnalogChannel _potentiometer = new AnalogChannel(PortMapFile.getInstance().getPort("Pot", 1));
    private final DigitalInput _hallEffect = new DigitalInput(PortMapFile.getInstance().getPort("HallEffect", 1));
    private final Encoder _encoder = new Encoder(PortMapFile.getInstance().getPort("EncoderA", 2),
            PortMapFile.getInstance().getPort("EncoderB", 3));
    private final VexIntegratedMotorEncoder _frisbeePusherEncoder = new VexIntegratedMotorEncoder(1, (byte) 0x60, "speed", true);
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
    private final SpeedController _frisbeePusher = new Victor(PortMapFile.getInstance().getPort("FrisbeePusher", 7));
    private final RobotDrive _drive = new RobotDrive(_leftFront, _leftBack, _rightFront, _rightBack);
    private final Solenoid _loadIn = new Solenoid(PortMapFile.getInstance().getPort("LoadIn", 8));
    private final Solenoid _loadOut = new Solenoid(PortMapFile.getInstance().getPort("LoadOut", 7));
    private final Solenoid _gearUp = new Solenoid(PortMapFile.getInstance().getPort("GearUp", 4));
    private final Solenoid _gearDown = new Solenoid(PortMapFile.getInstance().getPort("GearDown", 3));
    private final Solenoid _bitchBarIn = new Solenoid(PortMapFile.getInstance().getPort("BitchBarIn", 5));
    private final Solenoid _bitchBarOut = new Solenoid(PortMapFile.getInstance().getPort("BitchBarOut", 6));
    private final Solenoid _backLeft = new Solenoid(PortMapFile.getInstance().getPort("BackLeft", 2));
    private final Solenoid _backRight = new Solenoid(PortMapFile.getInstance().getPort("BackRight", 1));
    // Robot //
    private final TransferRateCalculator transferRate = new TransferRateCalculator();
    private final DigitalLimitSwitchModule psi120 = new DigitalLimitSwitchModule(_psi120);
    private final DigitalLimitSwitchModule psi60 = new DigitalLimitSwitchModule(_psi60);
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
    private final SpeedControllerModule frisbeePusher = new SpeedControllerModule(_frisbeePusher);
    private final SolenoidModule loadIn = new SolenoidModule(_loadIn);
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
            new Shooter(loadIn, loadOut, potentiometer, alignmentMotor, shooterController, _frisbeePusherEncoder, frisbeePusher);
    private final GearShifters gearShifterController =
            new GearShifters(gearDown, gearUp);
    private final ReversingSolenoids bitchBar =
            new ReversingSolenoids(bitchBarIn, bitchBarOut);
    // GOING WITH SINGLE-ACTION RIGHT NOW...
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
        ARPM.create();
        BSetpoint.create();
        BRPM.create();
        XSetpoint.create();
        XRPM.create();
        YSetpoint.create();
        YRPM.create();
        BackSetpoint.create();

        compressor.enable();

        _frisbeePusherEncoder.reset();

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
        psi120.disable();
        psi60.disable();
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
    private final Bindable.BindAction forwards = new Bindable.SetAxis(
            new ArcadeBinding(drive, ArcadeBinding.FORWARD), joystick1.getLeftDistanceFromMiddle());

    private void doTeleopBinds() {
        joystick1.removeAllBinds();
        joystick2.removeAllBinds();

        // Driving
        joystick1.addWhenPressed(joystick1.getLeftBumper(),
                new GearShiftCommand(gearShifterController, GearShiftCommand.FIRST, false));
        joystick1.addWhenPressed(joystick1.getRightBumper(),
                new GearShiftCommand(gearShifterController, GearShiftCommand.SECOND, false));
        joystick1.addWhenPressed(joystick1.getAxisAsButton(XboxController.TRIGGERS, -triggerShotThreashold),
                new AutoShoot(shotController, shooterController, true));
        joystick1.addWhenPressed(joystick1.getAxisAsButton(XboxController.TRIGGERS, triggerShotThreashold),
                new ShootCommand(shotController, true));
        joystick1.addWhenPressed(joystick1.getAButton(),
                new SwitchBitchBar(bitchBar, false));
        // Always bring alignment in when bitch bar is changed
        joystick1.addWhenPressed(joystick1.getAButton(),
                new AlignCommand(alignment, AlignCommand.COLLAPSE, false));
        joystick1.addWhenPressed(joystick1.getRightJoystickButton(),
                new AlignCommand(alignment, AlignCommand.REVERSE, false));
        // Always bring bitch bar in when alignment is changed
        joystick1.addWhenPressed(joystick1.getRightJoystickButton(),
                new SwitchBitchBar(bitchBar, SwitchBitchBar.IN, false));
        joystick1.addWhenPressed(joystick1.getLeftJoystickButton(),
                new AlignCommand(alignment, AlignCommand.LEFT, false));
        // Always bring bitch bar in when alignment is changed
        joystick1.addWhenPressed(joystick1.getLeftJoystickButton(),
                new SwitchBitchBar(bitchBar, SwitchBitchBar.IN, false));
        joystick1.addBind(forwards);
        joystick1.addAxis(joystick1.getRightX(),
                new ArcadeBinding(drive, ArcadeBinding.ROTATE));
        joystick1.addWhenPressed(joystick1.getBackButton(),
                new AlignShooter(shotController, BackSetpoint, true));
        joystick1.addAxis(joystick1.getDirectionalPad(),
                new SetFrisbeePusher(shotController));
        // Shooter
        joystick2.addWhenPressed(joystick2.getLeftBumper(),
                new ChangeRPMCommand(RPM, -shooterRPMSpeedChange, shooterController, false));
        joystick2.addWhenPressed(joystick2.getRightBumper(),
                new ChangeRPMCommand(RPM, +shooterRPMSpeedChange, shooterController, false));
        joystick2.addAxis(joystick2.getTriggers(),
                new SpeedControllerBinding(alignmentMotor, true));
        joystick2.addWhenPressed(joystick2.getAButton(),
                new AlignShooter(shotController, ASetpoint, true));
        joystick2.addWhenPressed(joystick2.getAButton(),
                new SetRPMCommand(RPM, ARPM, shooterController, false));
        joystick2.addWhenPressed(joystick2.getBButton(),
                new AlignShooter(shotController, BSetpoint, true));
        joystick2.addWhenPressed(joystick2.getBButton(),
                new SetRPMCommand(RPM, BRPM, shooterController, false));
        joystick2.addWhenPressed(joystick2.getXButton(),
                new AlignShooter(shotController, XSetpoint, true));
        joystick2.addWhenPressed(joystick2.getXButton(),
                new SetRPMCommand(RPM, XRPM, shooterController, false));
        joystick2.addWhenPressed(joystick2.getYButton(),
                new AlignShooter(shotController, YSetpoint, true));
        joystick2.addWhenPressed(joystick2.getYButton(),
                new SetRPMCommand(RPM, YRPM, shooterController, false));
        joystick2.addWhenPressed(joystick2.getLeftJoystickButton(),
                new Command() {
            public void run() {
                joystick1.removeBind(forwards);
                joystick1.removeAxisBinds(XboxController.RIGHT_X);

                joystick2.addAxis(joystick2.getRightY(),
                        new SideBinding(drive, SideBinding.LEFT), fineAdjustmentCoefficient);
            }
        });
        joystick2.addWhenReleased(joystick2.getLeftJoystickButton(),
                new Command() {
            public void run() {
                joystick2.removeAxisBinds(XboxController.RIGHT_Y);

                joystick1.addBind(forwards);
                joystick1.addAxis(joystick1.getRightX(),
                        new ArcadeBinding(drive, ArcadeBinding.ROTATE));
            }
        });
        joystick2.addWhenPressed(joystick2.getStartButton(),
                new BangBangCommand(shooterController, RPM, false));
        joystick2.addWhenPressed(joystick2.getStartButton(),
                new ReversingSolenoidsCommand(loadIn, loadOut,
                ReversingSolenoidsCommand.OUT, false));
        joystick2.addWhenPressed(joystick2.getBackButton(),
                new BangBangCommand(shooterController, 0, false));
        joystick2.addWhenPressed(joystick2.getBackButton(),
                new ReversingSolenoidsCommand(loadIn, loadOut,
                ReversingSolenoidsCommand.IN, false));

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
            psi60.enable();
            psi120.enable();
            encoder.enable();
            gyro.enable();
            bitchBar.enable();
            alignment.enable();
            gearShifterController.enable();
            drive.enable();
            frisbeePusher.enable();
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
                SmartDashboard.putBoolean("60 PSI", !psi60.isPushed());
                SmartDashboard.putBoolean("120 PSI", !psi120.isPushed());
                SmartDashboard.putBoolean("BBOut", !bitchBar.isIn());
                SmartDashboard.putBoolean("AlignOut", alignment.isOut());
                SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
                SmartDashboard.putNumber("Pusher", _frisbeePusherEncoder.getRevs());
                SmartDashboard.putNumber("Distance", encoder.getDistance());
                SmartDashboard.putNumber("Angle", gyro.getAngle());
                SmartDashboard.putNumber("ShooterPosition", potentiometer.getPosition());
                SmartDashboard.putNumber("NetworkLag", transferRate.packetsPerMillisecond());
                SmartDashboard.putNumber("Gear", gearShifterController.gear());
            }

            if (!psi120.isPushed()) {
                compressor.set(Relay.Value.kForward);
            } else {
                compressor.set(Relay.Value.kOff);
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
            psi60.enable();
            psi120.enable();
            bitchBar.enable();
            alignment.enable();
            gearShifterController.enable();
            drive.enable();
            frisbeePusher.enable();
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
                SmartDashboard.putBoolean("60 PSI", !psi60.isPushed());
                SmartDashboard.putBoolean("120 PSI", !psi120.isPushed());
                SmartDashboard.putBoolean("BBOut", !bitchBar.isIn());
                SmartDashboard.putBoolean("AlignOut", alignment.isOut());
                SmartDashboard.putNumber("HallEffectRate", hallEffect.getRate());
                SmartDashboard.putNumber("ShooterPosition", potentiometer.getPosition());
                SmartDashboard.putNumber("Gear", gearShifterController.gear());
            }
            if (!psi120.isPushed()) {
                compressor.set(Relay.Value.kForward);
            } else {
                compressor.set(Relay.Value.kOff);
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
