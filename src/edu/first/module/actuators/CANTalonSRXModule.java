package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

/**
 * The general purpose class that manipulates the Talon SRX speed controller
 * through CAN.
 *
 * @since May 28 13
 * @author Joel Gallant
 */
public class CANTalonSRXModule extends Module.StandardModule implements SpeedController {

    private final CANTalon talon;

    /**
     * Constructs the module with the talon object underneath this class to call
     * methods from.
     *
     * @param talon the composing instance which perform the functions
     * @param mode the type of input you are giving the talon
     * @throws NullPointerException when talon is null
     */
    public CANTalonSRXModule(CANTalon talon, ControlMode mode) {
        if (talon == null) {
            throw new NullPointerException("Null talon given");
        }
        this.talon = talon;

        this.talon.disableControl();
        this.talon.changeControlMode(mode);
    }

    /**
     * Constructs the module with the talon object underneath this class to call
     * methods from.
     *
     * @param devNum device ID number (see roborio-XXXX.local for details)
     * @param mode the type of input you are giving the talon
     */
    public CANTalonSRXModule(int devNum, ControlMode mode) {
        this(new CANTalon(devNum), mode);
    }

    /**
     * Constructs the module with the talon object underneath this class to call
     * methods from.
     *
     * @param devNum device ID number (see roborio-XXXX.local for details)
     * @param controlPeriodMS
     * @param mode the type of input you are giving the talon
     */
    public CANTalonSRXModule(int devNum, int controlPeriodMS, ControlMode mode) {
        this(new CANTalon(devNum, controlPeriodMS), mode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(double speed) {
        ControlMode old = talon.getControlMode();
        if (old != ControlMode.PercentVbus) {
            talon.changeControlMode(ControlMode.PercentVbus);
            set(speed);
            talon.changeControlMode(old);
        } else {
            set(speed);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRawSpeed(int speed) {
        if (speed >= 127) {
            set(((double) speed - 127.0) / 127.0);
        } else if (speed < 127) {
            set(-1.0 + ((double) speed / 127.0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        double speed;
        ControlMode old = talon.getControlMode();
        if (old != ControlMode.PercentVbus) {
            talon.changeControlMode(ControlMode.PercentVbus);
            speed = talon.get();
            talon.changeControlMode(old);
        } else {
            speed = talon.get();
        }
        return speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRawSpeed() {
        return (int) ((getSpeed() + 1) / 2 * 255);
    }

    /**
     * Has no effect on the Talon SRX.
     */
    @Override
    public void update() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRate(double rate) {
        setSpeed(rate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(double value) {
        talon.set(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRate() {
        return getSpeed();
    }

    /**
     * Will return different results based on the current control mode.
     *
     * <ul>
     * <li> In Current mode: returns output current.
     * <li> In Speed mode: returns current speed.
     * <li> In Position mode: returns current sensor position.
     * <li> In PercentVbus and Follower modes: returns current applied throttle.
     * [-1 to +1]
     * </ul>
     */
    @Override
    public double get() {
        return talon.get();
    }

    /**
     * Enables the operation of the Talon. Needs to be called before output is
     * given.
     */
    @Override
    protected void enableModule() {
        talon.enableControl();
    }

    /**
     * Disables output from the Talon.
     */
    @Override
    protected void disableModule() {
        talon.disableControl();
    }
}
