package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

/**
 * The general purpose class that manipulates the Talon SRX speed controller
 * through CAN.
 *
 * See here for a much more comprehensive guide:
 * http://www.crosstheroadelectronics.com/Talon%20SRX%20Software%20Reference%20Manual.pdf
 *
 * @since Jan 28 15
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
     * Will set output based on the current control mode.
     *
     * <ul>
     * <li> In PercentVbus, the output is between -1.0 and 1.0, with 0.0 as
     * stopped.
     * <li> In Follower mode, the output is the integer device ID of the talon
     * to duplicate.
     * <li> In Voltage mode, outputValue is in volts.
     * <li> In Current mode, outputValue is in amperes.
     * <li> In Speed mode, outputValue is in position change / 10ms.
     * <li> In Position mode, outputValue is in encoder ticks or an analog
     * value, depending on the sensor.
     * </ul>
     */
    @Override
    public void set(double value) {
        ensureEnabled();
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
     * Sets how you would like {@link #set(double)} and related methods to
     * behave.
     *
     * @param mode way to control your talon
     */
    public void setControlMode(ControlMode mode) {
        talon.changeControlMode(mode);
    }

    /**
     * Flips the sign (multiplies by negative one) the sensor values going into
     * the talon.
     *
     * This only affects position and velocity closed loop control. Allows for
     * situations where you may have a sensor flipped and going in the wrong
     * direction.
     *
     * @param flip if sensor input should be flipped
     */
    public void reverseSensor(boolean flip) {
        talon.reverseSensor(flip);
    }

    /**
     * Flips the sign (multiplies by negative one) the throttle values going
     * into the motor on the talon in closed loop modes.
     *
     * @param flip if motor output should be flipped
     */
    public void reverseOutput(boolean flip) {
        talon.reverseOutput(flip);
    }

    /**
     * Get the current encoder position, regardless of whether it is the current
     * feedback device.
     *
     * @return the current position of the encoder
     */
    public int getEncoderPosition() {
        return talon.getEncPosition();
    }

    /**
     * Get the current encoder speed, regardless of whether it is the current
     * feedback device.
     *
     * @return the current speed of the encoder
     */
    public int getEncoderSpeed() {
        return talon.getEncVelocity();
    }

    /**
     * Get the current analog in position, regardless of whether it is the
     * current feedback device.
     *
     * @return The 24bit analog position. The bottom ten bits is the ADC (0 -
     * 1023) on the analog pin of the Talon. The upper 14 bits tracks the
     * overflows and underflows (continuous sensor).
     */
    public int getAnalogInPosition() {
        return talon.getAnalogInPosition();
    }

    /**
     * Get the current analog input velocity, regardless of whether it is the
     * current feedback device.
     *
     * @return the current speed of the analog in device
     */
    public int getAnalogInVelocity() {
        return talon.getAnalogInVelocity();
    }

    /**
     * Get the current difference between the setpoint and the sensor value.
     *
     * @return the error, in whatever units are appropriate
     */
    public int getError() {
        return talon.getClosedLoopError();
    }

    /**
     * Returns temperature of Talon, in degrees Celsius.
     *
     * @return temperature in degrees
     */
    public double getTemperature() {
        return talon.getTemp();
    }

    /**
     * Returns the current going through the Talon, in Amperes.
     *
     * @return output of the talon in amps
     */
    public double getOutputCurrent() {
        return talon.getOutputCurrent();
    }

    /**
     * Returns the voltage going through the Talon, in volts.
     *
     * @return output by the talon in volts
     */
    public double getOutputVoltage() {
        return talon.getOutputVoltage();
    }

    /**
     * Returns the voltage of the input.
     *
     * @return voltage at the battery terminals of the talon in volts
     */
    public double getInputVoltage() {
        return talon.getBusVoltage();
    }

    /**
     * Returns the position of the sensor currently providing feedback. When
     * using analog sensors, 0 units corresponds to 0V, 1023 units corresponds
     * to 3.3V When using an analog encoder (wrapping around 1023 to 0 is
     * possible) the units are still 3.3V per 1023 units. When using quadrature,
     * each unit is a quadrature edge (4X) mode.
     *
     * @return sensor feedback
     */
    public double getSensorPosition() {
        return talon.getPosition();
    }

    /**
     * Sets the position of the sensor.
     *
     * @param pos sensor position
     */
    public void setSensorPosition(double pos) {
        talon.setPosition(pos);
    }

    /**
     * Sets what kind of sensor you are using.
     *
     * @param device sensor type
     */
    public void setFeedbackDevice(CANTalon.FeedbackDevice device) {
        talon.setFeedbackDevice(device);
    }

    /**
     * Set the voltage ramp rate for the current profile.
     *
     * Limits the rate at which the throttle will change. Affects all modes.
     *
     * @param rampRate Maximum change in voltage, in volts / sec.
     */
    public void setVoltageRampRate(double rampRate) {
        talon.setVoltageRampRate(rampRate);
    }

    /**
     * Set the closed loop ramp rate for the current profile.
     *
     * Limits the rate at which the throttle will change. Only affects position
     * and speed closed loop modes.
     *
     * @param rampRate Maximum change in voltage, in volts / sec.
     */
    public void setCloseLoopRampRate(double rampRate) {
        talon.setCloseLoopRampRate(rampRate);
    }

    /**
     * Sets whether you want your Talon to brake by default.
     *
     * @param brake braking by shorting motor
     */
    public void enableBrakeMode(boolean brake) {
        talon.enableBrakeMode(brake);
    }

    /**
     * Sets whether limit switches should be used by the Talon.
     *
     * @param forward fwd limit switch
     * @param reverse rev limit switch
     */
    public void enableLimitSwitch(boolean forward, boolean reverse) {
        talon.enableLimitSwitch(forward, reverse);
    }

    /**
     * Configure the fwd limit switch to be normally open or normally closed.
     * Talon will disable momentarilly if the Talon's current setting is
     * dissimilar to the caller's requested setting.
     *
     * Since Talon saves setting to flash this should only affect a given Talon
     * initially during robot install.
     *
     * @param normallyOpen true for normally open. false for normally closed.
     */
    public void fwdLimitSwitchNormallyOpen(boolean normallyOpen) {
        talon.ConfigFwdLimitSwitchNormallyOpen(normallyOpen);
    }

    /**
     * Configure the rev limit switch to be normally open or normally closed.
     * Talon will disable momentarilly if the Talon's current setting is
     * dissimilar to the caller's requested setting.
     *
     * Since Talon saves setting to flash this should only affect a given Talon
     * initially during robot install.
     *
     * @param normallyOpen true for normally open. false for normally closed.
     */
    public void revLimitSwitchNormallyOpen(boolean normallyOpen) {
        talon.ConfigRevLimitSwitchNormallyOpen(normallyOpen);
    }

    /**
     * Clears all stick faults in CAN.
     */
    public void clearStickyFaults() {
        talon.clearStickyFaults();
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

    /**
     * Sets control values for closed loop control.
     *
     * @param p proportional constant
     * @param i integration constant
     * @param d differential constant
     * @param f feedforward constant
     * @param izone Integration zone -- prevents accumulation of integration
     * error with large errors. Setting this to zero will ignore any izone
     * stuff.
     * @param closeLoopRampRate Closed loop ramp rate. Maximum change in
     * voltage, in volts / sec.
     * @param profile which profile to set the pid constants for. You can have
     * two profiles, with values of 0 or 1, allowing you to keep a second set of
     * values on hand in the talon. In order to switch profiles without
     * recalling setPID, you must call setProfile().
     */
    public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile) {
        talon.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
    }

    /**
     * Sets control values for closed loop control.
     *
     * @param p proportional constant
     * @param i integration constant
     * @param d differential constant
     */
    public void setPID(double p, double i, double d) {
        talon.setPID(p, i, d);
    }

    /**
     * @return The latest value set using set().
     */
    public double getSetpoint() {
        return talon.getSetpoint();
    }

    /**
     * Get the current proportional constant.
     *
     * @return proportional constant for current profile
     */
    public double getP() {
        return talon.getP();
    }

    /**
     * Get the current integral constant.
     *
     * @return integral constant for current profile
     */
    public double getI() {
        return talon.getI();
    }

    /**
     * Get the current "I Zone". The I Accumulator is automatically cleared when
     * the closed-loop error is outside the "I Zone"
     *
     * @return i-zone
     */
    public double getIZone() {
        return talon.getIZone();
    }

    /**
     * Get the current derivative constant.
     *
     * @return derivative constant for current profile
     */
    public double getD() {
        return talon.getD();
    }

    /**
     * Get the current feedforwards constant.
     *
     * @return feedforwards constant for current profile
     */
    public double getF() {
        return talon.getF();
    }
}
