package edu.ATA.twolf.subsystems;

import edu.first.module.Module;
import edu.first.module.sensor.DigitalLimitSwitchModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.speedcontroller.SpikeRelay;
import edu.first.module.speedcontroller.SpikeRelayModule;
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

    private final SpikeRelayModule loader;
    private final DigitalLimitSwitchModule psiSwitch;
    private final PotentiometerModule pot;
    private final DigitalLimitSwitchModule limitSwitch;
    private final SpeedControllerModule alignment;
    private final BangBangModule bangBang;

    /**
     *
     * @param loader
     * @param reloader
     * @param pot
     * @param alignment
     */
    public Shooter(SpikeRelayModule loader, DigitalLimitSwitchModule psiSwitch, PotentiometerModule pot,
            DigitalLimitSwitchModule limitSwitch,
            SpeedControllerModule alignment, BangBangModule bangBang) {
        super(new Module[]{loader, psiSwitch, pot, limitSwitch, alignment});
        this.loader = loader;
        this.psiSwitch = psiSwitch;
        this.pot = pot;
        this.limitSwitch = limitSwitch;
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
            loader.set(SpikeRelay.BACKWARD);
            Timer.delay(0.5);
            loader.set(false);
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
                if (!set(+1)) {
                    break;
                }
            }
        } else if (pot.getPosition() < setpoint) {
            while (pot.getPosition() < setpoint && DriverstationInfo.getGamePeriod().equals(mode)) {
                if (!set(-1)) {
                    break;
                }
            }
        }
        if (Math.abs(pot.getPosition() - setpoint) > 0.1) {
            // Retry for overshoot
            alignTo(setpoint);
        }
        set(0);
        Logger.log(Logger.Urgency.USERMESSAGE, "Aligned to " + setpoint);

    }

    private boolean set(double speed) {
        // Speed < 0 == Shooter moving upwards
        if (speed < 0 && limitSwitch.isPushed()) {
            alignment.set(0);
            return false;
        } else {
            alignment.set(speed);
            return true;
        }
    }
}
