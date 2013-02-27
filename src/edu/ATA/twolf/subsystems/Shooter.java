package edu.ATA.twolf.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.sensor.DigitalLimitSwitchModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.BangBangModule;
import edu.first.utils.DriverstationInfo;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Team 4334
 */
public final class Shooter extends Subsystem {

    private final SolenoidModule loadIn, loadOut;
    private final DigitalLimitSwitchModule psiSwitch;
    private final PotentiometerModule pot;
    private final SpeedControllerModule alignment;
    private final BangBangModule bangBang;

    public Shooter(SolenoidModule loadIn, SolenoidModule loadOut,
            DigitalLimitSwitchModule psiSwitch, PotentiometerModule pot,
            SpeedControllerModule alignment, BangBangModule bangBang) {
        super(new Module[]{loadIn, loadOut, psiSwitch, pot, alignment, bangBang});
        this.loadIn = loadIn;
        this.loadOut = loadOut;
        this.psiSwitch = psiSwitch;
        this.pot = pot;
        this.alignment = alignment;
        this.bangBang = bangBang;
    }

    /**
     *
     */
    public void shoot() {
        if (!psiSwitch.isPushed()) {
            Logger.log(Logger.Urgency.STATUSREPORT, "Shooting");
            Timer.delay(0.2);
            bangBang.setCoast(true);
            loadIn.set(false);
            loadOut.set(true);
            Timer.delay(0.5);
            loadIn.set(true);
            loadOut.set(false);
            bangBang.setCoast(false);
        } else if (psiSwitch.isPushed()) {
            Logger.log(Logger.Urgency.URGENT, "PSI not high enough");
        }
    }

    /**
     *
     * @param setpoint
     */
    public void alignTo(final double setpoint) {
        Logger.log(Logger.Urgency.USERMESSAGE, "Aligning to position");
        final String mode = DriverstationInfo.getGamePeriod();
        if (pot.getPosition() > setpoint) {
            while (pot.getPosition() > setpoint && DriverstationInfo.getGamePeriod().equals(mode)) {
                alignment.set(+1);
                Timer.delay(0.02);
            }
        } else if (pot.getPosition() < setpoint) {
            while (pot.getPosition() < setpoint && DriverstationInfo.getGamePeriod().equals(mode)) {
                alignment.set(-1);
                Timer.delay(0.02);
            }
        }
        if (Math.abs(pot.getPosition() - setpoint) > 0.1) {
            // Retry for overshoot
            alignTo(setpoint);
        }
        alignment.set(0);
        Logger.log(Logger.Urgency.USERMESSAGE, "Aligned to " + setpoint);
    }
}
