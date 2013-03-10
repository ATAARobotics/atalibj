package edu.ata.subsystems;

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
 * Shooter subsystem that does everything to control the shooter. Includes the
 * loader and winch, but not the motor control for the shooter.
 *
 * @author Team 4334
 */
public final class Shooter extends Subsystem {

    private static final double retryThreshold = 0.1;
    private final SolenoidModule loadIn, loadOut;
    private final DigitalLimitSwitchModule psiSwitch;
    private final PotentiometerModule pot;
    private final SpeedControllerModule alignment;
    private final BangBangModule bangBang;

    /**
     * Constructs the shooter using all of the components used.
     *
     * @param loadIn loader in solenoid
     * @param loadOut loader out solenoid
     * @param psiSwitch 60 psi switch
     * @param pot potentiometer measuring alignment
     * @param alignment motor controlling winch
     * @param bangBang bang-bang controlling shooter wheel
     */
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
     * Constructs the shooter using all of the components used.
     *
     * @param loadOut loader out solenoid
     * @param psiSwitch 60 psi switch
     * @param pot potentiometer measuring alignment
     * @param alignment motor controlling winch
     * @param bangBang bang-bang controlling shooter wheel
     */
    public Shooter(SolenoidModule loadOut, DigitalLimitSwitchModule psiSwitch, PotentiometerModule pot,
            SpeedControllerModule alignment, BangBangModule bangBang) {
        super(new Module[]{loadOut, psiSwitch, pot, alignment, bangBang});
        this.loadIn = null;
        this.loadOut = loadOut;
        this.psiSwitch = psiSwitch;
        this.pot = pot;
        this.alignment = alignment;
        this.bangBang = bangBang;
    }

    /**
     * Coasts the shooter and fires the disc for half a second.
     */
    public void shoot() {
        Logger.log(Logger.Urgency.STATUSREPORT, "Shooting");
        bangBang.setCoast(true);
        if (loadIn != null) {
            loadIn.set(false);
        }
        loadOut.set(true);
        Timer.delay(0.5);
        if (loadIn != null) {
            loadIn.set(true);
        }
        loadOut.set(false);
        bangBang.setCoast(false);
    }

    /**
     * Aligns the shooter to a position. Is thread-locked and can only be run
     * once at a time. If it already running, this method will wait until the
     * other instance has finished and will run after it.
     *
     * @param setpoint
     */
    public void alignTo(final double setpoint) {
        synchronized (this) {
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
            if (Math.abs(pot.getPosition() - setpoint) > retryThreshold) {
                // Retry for overshoot
                alignTo(setpoint);
            }
            alignment.set(0);
            Logger.log(Logger.Urgency.USERMESSAGE, "Aligned to " + setpoint);
        }
    }
}
