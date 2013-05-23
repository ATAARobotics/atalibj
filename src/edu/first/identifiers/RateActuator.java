package edu.first.identifiers;

/**
 * The general contract for classes that actuate to a rate. Uses a number as the
 * setpoint rate. This rate should be maintained virtually until something else
 * is called.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface RateActuator extends Output {

    /**
     * Sets the actuator to a rate. There should be no delay between the time
     * this method is called and when the actuator starts going there, but it is
     * possible that the finishing of this method does not mean that it has
     * achieved the rate. That case should be well documented and explained.
     *
     * @param rate new rate to aim for
     */
    public void setRate(double rate);
}
