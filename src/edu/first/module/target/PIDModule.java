package edu.first.module.target;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.PIDController;

/**
 * Module designed to use a PID controller (see
 * http://www.chiefdelphi.com/forums/showthread.php?threadid=110268) to use
 * setpoints to attain the correct position or speed.
 *
 * @author Joel Gallant
 */
public final class PIDModule extends ForwardingPIDController implements Module.DisableableModule {

    /**
     * Constructs the object by using composition, using the given controller
     * object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    public PIDModule(PIDController controller) {
        super(controller);
    }

    /**
     * Enables the controller. Is the same thing as
     * {@link edu.wpi.first.wpilibj.PIDController#enable()}. Begins the
     * execution of the PID task.
     *
     * @return whether controller has successfully enabled
     */
    public boolean enable() {
        getController().enable();
        return isEnabled();
    }

    /**
     * Returns whether the controller is enabled (running).
     *
     * @return if controller is running
     */
    public boolean isEnabled() {
        return getController().isEnable();
    }

    /**
     * Stops the controller from running. Is the same thing as
     * {@link edu.wpi.first.wpilibj.PIDController#disable()}. Prevents
     * controller from moving the PIDOutput.
     *
     * @return whether controller has successfully disabled
     */
    public boolean disable() {
        getController().disable();
        return !isEnabled();
    }
}

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.PIDController}.
 *
 * @author Joel Gallant
 */
class ForwardingPIDController implements edu.first.module.target.PIDController {

    private final edu.wpi.first.wpilibj.PIDController controller;
    private double tolerance = 0;

    /**
     * Constructs the object by using composition, using the given controller
     * object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    ForwardingPIDController(edu.wpi.first.wpilibj.PIDController controller) {
        if (controller == null) {
            throw new NullPointerException();
        }
        this.controller = controller;
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.PIDController}.
     *
     * @return composition object under this one
     */
    protected edu.wpi.first.wpilibj.PIDController getController() {
        return controller;
    }

    /**
     * Set the PID Controller gain parameters. Set the proportional, integral,
     * and differential coefficients.
     *
     * @param p proportional coefficient
     * @param i integral coefficient
     * @param d differential coefficient
     */
    public void setPID(double p, double i, double d) {
        controller.setPID(p, i, d);
    }

    /**
     * Set the PID Controller feel forward coefficient.
     *
     * @param f feed forward coefficient
     */
    public void setF(double f) {
        controller.setPID(getP(), getI(), getD(), f);
    }

    /**
     * Get the Proportional coefficient.
     *
     * @return proportional coefficient
     */
    public double getP() {
        return controller.getP();
    }

    /**
     * Get the Integral coefficient.
     *
     * @return integral coefficient
     */
    public double getI() {
        return controller.getI();
    }

    /**
     * Get the Differential coefficient.
     *
     * @return differential coefficient
     */
    public double getD() {
        return controller.getD();
    }

    /**
     * Get the Feed forward coefficient.
     *
     * @return feed forward coefficient
     */
    public double getF() {
        return controller.getF();
    }

    /**
     * Sets the maximum and minimum values expected from the input.
     *
     * @param minimumInput the minimum percentage expected from the input
     * @param maximumInput the maximum percentage expected from the output
     */
    public void setInputRange(double minimumInput, double maximumInput) {
        controller.setInputRange(minimumInput, maximumInput);
    }

    /**
     * Sets the minimum and maximum values to write.
     *
     * @param minimumOutput the minimum percentage to write to the output
     * @param maximumOutput the maximum percentage to write to the output
     */
    public void setOutputRange(double minimumOutput, double maximumOutput) {
        controller.setOutputRange(minimumOutput, maximumOutput);
    }

    /**
     * Set the setpoint for the PIDController.
     *
     * @param setpoint the desired setpoint
     */
    public void setSetpoint(double setpoint) {
        controller.setSetpoint(setpoint);
    }

    /**
     * Returns the current setpoint of the PIDController.
     *
     * @return the current setpoint
     */
    public double getSetpoint() {
        return controller.getSetpoint();
    }

    /**
     * Return true if the error is within the percentage of the total input
     * range, determined by {@link PIDModule#setPercentTolerance(double)}. This
     * assumes that the maximum and minimum input were set using setInput.
     *
     * @return true if the error is less than the tolerance
     */
    public boolean onTarget() {
        return Math.abs(controller.getError()) < tolerance;
    }

    /**
     * Set the percentage error which is considered tolerable for use with
     * OnTarget.
     *
     * @param percent error which is tolerable
     */
    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }
}
