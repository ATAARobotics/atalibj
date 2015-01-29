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

    @Override
    public void init() {
    }

    @Override
    /**
     * Do not use, not implemented.
     */
    public @Deprecated
    void setSpeed(double speed) {
    }

    @Override
    public void setRawSpeed(int speed) {
    }

    @Override
    public double getSpeed() {
        return get();
    }

    @Override
    /**
     * Do not use, not implemented.
     */
    public @Deprecated
    int getRawSpeed() {
        return 0;
    }

    @Override
    public void update() {
    }

    @Override
    public void setRate(double rate) {
        set(rate);
    }

    @Override
    public void set(double value) {
        talon.set(value);
    }

    @Override
    public double getRate() {
        return get();
    }

    @Override
    public double get() {
        return talon.get();
    }

    @Override
    protected void enableModule() {
        talon.enableControl();
    }

    @Override
    protected void disableModule() {
        talon.disable();
        talon.disableControl();
    }
}
