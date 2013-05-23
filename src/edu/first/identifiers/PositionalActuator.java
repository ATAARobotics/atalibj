package edu.first.identifiers;

/**
 * The general contract for classes that actuate to a position. Uses a number as
 * the setpoint position.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface PositionalActuator extends Output {

    /**
     * Sets the actuator to a position. There should be no delay between the
     * time this method is called and when the actuator starts going there, but
     * it is possible that the finishing of this method does not mean that it
     * has achieved the position. That case should be well documented and
     * explained.
     *
     * @param position new position to set the actuator to
     */
    public void setPosition(double position);
}
