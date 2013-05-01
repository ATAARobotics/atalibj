package edu.ata.subsystems;

import edu.ata.murdock.Murdock;
import edu.first.module.Module;
import edu.first.module.sensor.DigitalLimitSwitchModule;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.sensor.GyroModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.utils.DriverstationInfo;
import edu.first.utils.TransferRateCalculator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class SmartDashboardSender extends Subsystem {

    private static final long delay = 100L;
    private final ShooterWheel shooterWheel;
    private final DigitalLimitSwitchModule psi60;
    private final DigitalLimitSwitchModule psi120;
    private final BitchBar bitchBar;
    private final AlignmentSystem alignmentSystem;
    private final Winch winch;
    private final GearShifters gearShifters;
    private final WindshieldWiper windshieldWiper;
    private final EncoderModule encoder;
    private final GyroModule gyro;
    private final TransferRateCalculator rateCalculator;

    public SmartDashboardSender(ShooterWheel shooterWheel, DigitalLimitSwitchModule psi60, 
            DigitalLimitSwitchModule psi120, BitchBar bitchBar, AlignmentSystem alignmentSystem, 
            Winch winch, GearShifters gearShifters, WindshieldWiper windshieldWiper,
            EncoderModule encoder, GyroModule gyro, TransferRateCalculator rateCalculator) {
<<<<<<< HEAD
        super(new Module[] {shooterWheel, psi60, psi120, bitchBar, alignmentSystem, winch, gearShifters});
=======
        super(new Module[] {shooterWheel, psi60, psi120, bitchBar, alignmentSystem, winch, 
            gearShifters, windshieldWiper, encoder, gyro});
>>>>>>> master
        this.shooterWheel = shooterWheel;
        this.psi60 = psi60;
        this.psi120 = psi120;
        this.bitchBar = bitchBar;
        this.alignmentSystem = alignmentSystem;
        this.winch = winch;
        this.gearShifters = gearShifters;
        this.windshieldWiper = windshieldWiper;
        this.encoder = encoder;
        this.gyro = gyro;
        this.rateCalculator = rateCalculator;
    }
    
    public void start() {
        startAtFixedDelay(delay);
    }

    public void run() {
        if (DriverstationInfo.getDS().getDigitalIn(Murdock.smartDashboardPort)) {
            // Normal smartdashboard info
            SmartDashboard.putBoolean("PastSetpoint", shooterWheel.isPastSetpoint());
            SmartDashboard.putBoolean("60 PSI", !psi60.isPushed());
            SmartDashboard.putBoolean("120 PSI", !psi120.isPushed());
            SmartDashboard.putBoolean("BBOut", !bitchBar.isOut());
            SmartDashboard.putBoolean("AlignOut", alignmentSystem.isOut());
            SmartDashboard.putNumber("RPM", shooterWheel.getRPM());
            SmartDashboard.putNumber("ShooterPosition", winch.getPosition());
            SmartDashboard.putNumber("RealShooterPosition", winch.getSensorPosition());
            SmartDashboard.putNumber("Gear", gearShifters.gear());
            
            if (!DriverstationInfo.getDS().getDigitalIn(Murdock.competitionPort)) {
                // Testing mode info
                SmartDashboard.putNumber("Distance", encoder.getDistance());
                SmartDashboard.putNumber("Angle", gyro.getAngle());
                SmartDashboard.putNumber("NetworkLag", rateCalculator.packetsPerMillisecond());
            }
        }
    }
}