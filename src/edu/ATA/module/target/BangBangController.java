package edu.ATA.module.target;

/**
 * Interface representing all bang-bang speed / position controllers.
 *
 * @author Joel Gallant
 */
public interface BangBangController {

    /**
     * Sets the speed / position that the controller is aiming for.
     *
     * @param setpoint position / speed to stop going at
     */
    public void setSetpoint(double setpoint);

    /**
     * Sets the maximum speed to set the output.
     *
     * @param maxSpeed maximum speed of output
     */
    public void setMaxSpeed(double maxSpeed);

    /**
     * Reverses the direction of the output. This is useful because bang bang
     * only goes in one direction.
     */
    public void reverse();
}
