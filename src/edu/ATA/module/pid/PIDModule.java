package edu.ATA.module.pid;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.PIDController;

/**
 * Module designed to use a PID controller (see
 * http://www.chiefdelphi.com/forums/showthread.php?threadid=110268) to use
 * setpoints to attain the correct position or speed.
 *
 * @author Joel Gallant
 */
public class PIDModule extends ForwardingPIDController implements Module.DisableableModule {

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
class ForwardingPIDController implements edu.ATA.module.pid.PIDController {

    private final edu.wpi.first.wpilibj.PIDController controller;

    /**
     * Constructs the object by using composition, using the given controller
     * object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    ForwardingPIDController(edu.wpi.first.wpilibj.PIDController controller) {
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
     * {@inheritDoc}
     */
    public void setPID(double p, double i, double d) {
        controller.setPID(p, i, d);
    }

    /**
     * {@inheritDoc}
     */
    public void setF(double f) {
        controller.setPID(getP(), getI(), getD(), f);
    }

    /**
     * {@inheritDoc}
     */
    public double getP() {
        return controller.getP();
    }

    /**
     * {@inheritDoc}
     */
    public double getI() {
        return controller.getI();
    }

    /**
     * {@inheritDoc}
     */
    public double getD() {
        return controller.getD();
    }

    /**
     * {@inheritDoc}
     */
    public double getF() {
        return controller.getF();
    }

    /**
     * {@inheritDoc}
     */
    public void setInputRange(double minimumInput, double maximumInput) {
        controller.setInputRange(minimumInput, maximumInput);
    }

    /**
     * {@inheritDoc}
     */
    public void setOutputRange(double minimumOutput, double maximumOutput) {
        controller.setOutputRange(minimumOutput, maximumOutput);
    }

    /**
     * {@inheritDoc}
     */
    public void setSetpoint(double setpoint) {
        controller.setSetpoint(setpoint);
    }

    /**
     * {@inheritDoc}
     */
    public double getSetpoint() {
        return controller.getSetpoint();
    }

    /**
     * {@inheritDoc}
     */
    public boolean onTarget() {
        return controller.onTarget();
    }

    /**
     * {@inheritDoc}
     */
    public void setPercentTolerance(double percentage) {
        controller.setPercentTolerance(percentage);
    }
}
