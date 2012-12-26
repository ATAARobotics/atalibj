package edu.ata.driving.modules;

import edu.ata.driving.modules.Module.Disableable;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Module class representing a PID controller. Is capable of setting all values
 * in the controller, and using a setpoint to find the target.
 *
 * @see PIDController
 * @author Joel Gallant
 */
public class PIDModule extends Module implements Disableable {

    private final PIDController pid;

    /**
     * Creates the PID module with a name and the {@link PIDController} object
     * to use underneath.
     *
     * @param name name of the module displayed to user
     * @param pid PID controller
     */
    public PIDModule(String name, PIDController pid) {
        super(name);
        this.pid = pid;
    }

    /**
     * Creates the PID module with a name and the underlining values, inputs and
     * outputs.
     *
     * @see PIDController#PIDController(double, double, double,
     * edu.wpi.first.wpilibj.PIDSource, edu.wpi.first.wpilibj.PIDOutput)
     * @param name name of the module displayed to user
     * @param Kp the proportional coefficient
     * @param Ki the integral coefficient
     * @param Kd the derivative coefficient
     * @param source input values for the controller
     * @param output output values for the controller
     */
    public PIDModule(String name, double Kp, double Ki, double Kd, PIDSource source, PIDOutput output) {
        this(name, new PIDController(Kp, Ki, Kd, source, output));
    }

    /**
     * Creates the PID module with a name and the underlining values, inputs and
     * outputs. Sets the period between loops.
     *
     * @see PIDController#PIDController(double, double, double,
     * edu.wpi.first.wpilibj.PIDSource, edu.wpi.first.wpilibj.PIDOutput, double)
     * @param name name of the module displayed to user
     * @param Kp the proportional coefficient
     * @param Ki the integral coefficient
     * @param Kd the derivative coefficient
     * @param source input values for the controller
     * @param output output values for the controller
     * @param period time between loops (default is 50ms)
     */
    public PIDModule(String name, double Kp, double Ki, double Kd, PIDSource source, PIDOutput output, double period) {
        this(name, new PIDController(Kp, Ki, Kd, source, output, period));
    }

    public boolean isEnabled() {
        return pid.isEnable();
    }

    public void enable() {
        pid.enable();
    }

    public void disable() {
        pid.disable();
    }

    /**
     * Set the percentage error which is considered tolerable for use with
     * onTarget(). (Input of 15.0 = 15 percent)
     *
     * @param percentage error which is tolerable
     */
    public void setTolerance(double percentage) {
        pid.setPercentTolerance(percentage);
    }

    /**
     * Sets the maximum and minimum values expected from the input.
     *
     * @param min the minimum percentage expected from the input
     * @param max the maximum percentage expected from the output
     */
    public void setInputRange(double min, double max) {
        pid.setInputRange(min, max);
    }

    /**
     * Sets the minimum and maximum values to write.
     *
     * @param min the minimum percentage to write to the output
     * @param max the maximum percentage to write to the output
     */
    public void setOutputRange(double min, double max) {
        pid.setOutputRange(min, max);
    }

    /**
     * Set the setpoint for the PIDController
     *
     * @param setpoint the desired setpoint
     */
    public void setSetpoint(double setpoint) {
        pid.setSetpoint(setpoint);
    }

    /**
     * Set the PID Controller gain parameters. Set the proportional, integral,
     * and differential coefficients.
     *
     * @param Kp Proportional coefficient
     * @param Ki Integral coefficient
     * @param Kd Differential coefficient
     */
    public void setPID(double Kp, double Ki, double Kd) {
        pid.setPID(Kp, Ki, Kd);
    }

    /**
     * Sets the F coefficient.
     *
     * @param Kf Feed forward coefficient
     */
    public void setF(double Kf) {
        pid.setPID(pid.getP(), pid.getI(), pid.getD(), Kf);
    }

    /**
     * Return true if the error is within the percentage of the total input
     * range, determined by setTolerance. This assumes that the maximum and
     * minimum input were set using setInput.
     *
     * @return true if the error is less than the tolerance
     */
    public boolean onTarget() {
        return pid.onTarget();
    }

    /**
     * Returns the current difference of the input from the setpoint
     *
     * @return the current error
     */
    public double getError() {
        return pid.getError();
    }
}
