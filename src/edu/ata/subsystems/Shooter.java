package edu.ata.subsystems;

import edu.ata.modules.AlignmentMotor;
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
    private final PotentiometerModule pot;
    private final AlignmentMotor alignment;
    private final BangBangModule bangBang;
    private boolean lock;

    /**
     * Constructs the shooter using all of the components used.
     *
     * @param loadIn loader in solenoid
     * @param loadOut loader out solenoid
     * @param pot potentiometer measuring alignment
     * @param alignment motor controlling winch
     * @param bangBang bang-bang controlling shooter wheel
     */
    public Shooter(SolenoidModule loadIn, SolenoidModule loadOut, PotentiometerModule pot,
            AlignmentMotor alignment, BangBangModule bangBang) {
        super(new Module[]{loadIn, loadOut, pot, alignment, bangBang});
        this.loadIn = loadIn;
        this.loadOut = loadOut;
        this.pot = pot;
        this.alignment = alignment;
        this.bangBang = bangBang;
    }

    /**
     * Constructs the shooter using all of the components used.
     *
     * @param loadOut loader out solenoid
     * @param pot potentiometer measuring alignment
     * @param alignment motor controlling winch
     * @param bangBang bang-bang controlling shooter wheel
     */
    public Shooter(SolenoidModule loadOut, PotentiometerModule pot,
            AlignmentMotor alignment, BangBangModule bangBang) {
        super(new Module[]{loadOut, pot, alignment, bangBang});
        this.loadIn = null;
        this.loadOut = loadOut;
        this.pot = pot;
        this.alignment = alignment;
        this.bangBang = bangBang;
    }

    /**
     * Coasts the shooter and fires the disc for half a second.
     */
    public void shoot() {
        Logger.log(Logger.Urgency.USERMESSAGE, "Shooting");
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
        final String mode = DriverstationInfo.getGamePeriod();
        if(!lock) {
            lock = true;
            SpeedControllerModule control = alignment.lock();
            Logger.log(Logger.Urgency.USERMESSAGE, "Aligning to " + setpoint);
            while (true) {
                if (pot.getPosition() > setpoint) {
                    while (pot.getPosition() > setpoint && DriverstationInfo.getGamePeriod().equals(mode)) {
                        control.set(-1);
                        Timer.delay(0.02);
                    }
                } else if (pot.getPosition() < setpoint) {
                    while (pot.getPosition() < setpoint && DriverstationInfo.getGamePeriod().equals(mode)) {
                        control.set(+1);
                        Timer.delay(0.02);
                    }
                }
                if (Math.abs(pot.getPosition() - setpoint) > retryThreshold && DriverstationInfo.getGamePeriod().equals(mode)) {
                    // Retry for overshoot
                    continue;
                } else {
                    break;
                }
            }
            alignment.unlock();
            lock = false;
            Logger.log(Logger.Urgency.USERMESSAGE, "Aligned to " + setpoint);
        }
    }
}
