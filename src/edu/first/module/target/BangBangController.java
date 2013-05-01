package edu.first.module.target;

import edu.first.identifiers.SetteableNumber;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Interface representing all bang-bang speed / position controllers.
 *
 * @author Joel Gallant
 */
public interface BangBangController extends SetteableNumber {

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

    /**
     * Returns the current setpoint of the bang bang controller.
     *
     * @return the current setpoint
     */
    public double getSetpoint();

    /**
     * Returns the value of the input.
     *
     * @return input from given {@link PIDSource}
     */
    public double getInput();
}
