package edu.ATA.module.pid;

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.PIDController}.
 *
 * @author Joel Gallant
 */
class ForwardingPIDController implements PIDController {

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
