package edu.ata.subsystems;

import edu.ata.modules.AlignmentMotor;
import edu.ata.murdock.Murdock;
import edu.first.module.Module;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.BangBangModule;
import edu.first.utils.DriverstationInfo;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shooter subsystem that does everything to control the shooter. Includes the
 * loader and winch, but not the motor control for the shooter.
 *
 * @author Team 4334
 */
public final class Shooter extends Subsystem {

    private static final double retryThreshold = 0.02;
    private static final double pusherSpeed = 0.4;
    private static final double in = 0.5;
    private final SolenoidModule loadIn, loadOut;
    private final PotentiometerModule pot;
    private final AlignmentMotor alignment;
    private final BangBangModule bangBang;
    private final VexIntegratedMotorEncoder frisbeePusherEncoder;
    private final SpeedControllerModule frisbeePusher;
    private int shotCount;
    private boolean shotLock, alignLock, pusherLock;

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
            AlignmentMotor alignment, BangBangModule bangBang, VexIntegratedMotorEncoder frisbeePusherEncoder,
            SpeedControllerModule frisbeePusher) {
        super(new Module[]{loadIn, loadOut, pot, alignment, bangBang, frisbeePusher});
        this.loadIn = loadIn;
        this.loadOut = loadOut;
        this.pot = pot;
        this.alignment = alignment;
        this.bangBang = bangBang;
        this.frisbeePusherEncoder = frisbeePusherEncoder;
        this.frisbeePusher = frisbeePusher;
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
            AlignmentMotor alignment, BangBangModule bangBang, VexIntegratedMotorEncoder frisbeePusherEncoder,
            SpeedControllerModule frisbeePusher) {
        super(new Module[]{loadOut, pot, alignment, bangBang, frisbeePusher});
        this.loadIn = null;
        this.loadOut = loadOut;
        this.pot = pot;
        this.alignment = alignment;
        this.bangBang = bangBang;
        this.frisbeePusherEncoder = frisbeePusherEncoder;
        this.frisbeePusher = frisbeePusher;
    }

    /**
     * Coasts the shooter and fires the disc for half a second.
     */
    public void shoot() {
        if (!shotLock) {
            shotCount++;
            shotLock = true;
            Logger.log(Logger.Urgency.USERMESSAGE, "Shooting");
            Logger.log(Logger.Urgency.LOG, "Shot @ " + DriverstationInfo.getGamePeriod() + " " + DriverstationInfo.getMatchTime());
            System.out.println("Speed before shot - " + bangBang.getInput());
            bangBang.setCoast(true);

            // Out
            if (!loadOut.get()) {
                loadOut.set(true);
                if (loadIn != null) {
                    loadIn.set(false);
                }
                Timer.delay(0.3);
            }

            // In
            if (loadIn != null) {
                loadIn.set(true);
            }
            loadOut.set(false);

            Timer.delay(0.5);


            System.out.println("Speed after shot - " + bangBang.getInput());

            // Out
            if (loadIn != null) {
                loadIn.set(false);
            }
            loadOut.set(true);

            bangBang.setCoast(false);
            shotLock = false;
            if (DriverstationInfo.getDS().getDigitalIn(Murdock.smartDashboardPort)) {
                SmartDashboard.putNumber("Shots", shotCount);
            }
        }
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
        final long start = System.currentTimeMillis();
        final double timeout = 6000 * Math.abs(pot.getPosition() - setpoint);
        final double P = 0.16;

        if (!alignLock) {
            alignLock = true;
            SpeedControllerModule control = alignment.lock();
            Logger.log(Logger.Urgency.USERMESSAGE, "Aligning to " + setpoint);
            while ((System.currentTimeMillis() - start) < timeout) {
                if (pot.getPosition() > setpoint) {
                    while (pot.getPosition() > setpoint && DriverstationInfo.getGamePeriod().equals(mode)
                            && (System.currentTimeMillis() - start) < timeout) {
                        control.set((pot.getPosition() - setpoint) / P);
                        Timer.delay(0.002);
                    }
                } else if (pot.getPosition() < setpoint) {
                    while (pot.getPosition() < setpoint && DriverstationInfo.getGamePeriod().equals(mode)
                            && (System.currentTimeMillis() - start) < timeout) {
                        control.set((pot.getPosition() - setpoint) / P);
                        Timer.delay(0.002);
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
            alignLock = false;
            Logger.log(Logger.Urgency.USERMESSAGE, "Aligned to " + setpoint);
        }
    }

    public void setFrisbeePusher(double speed) {
        if (!pusherLock) {
            frisbeePusher.set(speed * pusherSpeed);
        }
    }

    public void pushFrisbees() {
        if (!pusherLock) {
            pusherLock = true;
            frisbeePusherEncoder.reset();

            Timer t = new Timer();
            t.start();

            // Make sure rev is reset
            while ((frisbeePusherEncoder.getRevs() > 0 || frisbeePusherEncoder.getRevs() < 0)
                    && t.get() < 1) {
                Timer.delay(0.002);
            }

            t.reset();

            while (frisbeePusherEncoder.getRevs() < in && t.get() < 3) {
                frisbeePusher.set(0.4);
                Timer.delay(0.002);
            }
            frisbeePusher.set(0);
            frisbeePusher.set(-0.3);
            Timer.delay(0.01);

            t.reset();
            while (frisbeePusherEncoder.getRevs() > 0 && t.get() < 3) {
                frisbeePusher.set(-0.45);
                Timer.delay(0.002);
            }
            frisbeePusher.set(+0.3);
            Timer.delay(0.01);

            frisbeePusher.set(0);

            pusherLock = false;
        }
    }
}