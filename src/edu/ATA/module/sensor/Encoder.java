package edu.ATA.module.sensor;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * Interface that represents encoder sensors. Is meant to describe all of their
 * functionality, as well as making them a {@link PIDSource}.
 *
 * @author Joel Gallant
 */
interface Encoder extends PIDSource {

    /**
     * Start the Encoder. Starts counting pulses on the Encoder device.
     */
    public void start();

    /**
     * Stops counting pulses on the Encoder device. The value is not changed.
     */
    public void stop();

    /**
     * Reset the Encoder distance to zero. Resets the current count to zero on
     * the encoder.
     */
    public void reset();

    /**
     * Sets the maximum period for stopped detection. Sets the value that
     * represents the maximum period of the Encoder before it will assume that
     * the attached device is stopped. This timeout allows users to determine if
     * the wheels or other shaft has stopped rotating. This method compensates
     * for the decoding type.
     *
     *
     * @param maxPeriod the maximum time between rising and falling edges before
     * the FPGA will report the device stopped. This is expressed in seconds.
     */
    public void setMaxPeriod(double maxPeriod);

    /**
     * Determine if the encoder is stopped. Using the MaxPeriod value, a boolean
     * is returned that is true if the encoder is considered stopped and false
     * if it is still moving. A stopped encoder is one where the most recent
     * pulse width exceeds the MaxPeriod.
     *
     * @return true if the encoder is considered stopped.
     */
    public boolean getStopped();

    /**
     * Get the distance the robot has driven since the last reset.
     *
     * @return the distance driven since the last reset as scaled by the value
     * from setDistancePerPulse().
     */
    public double getDistance();

    /**
     * Get the current rate of the encoder. Units are distance per second as
     * scaled by the value from setDistancePerPulse().
     *
     * @return the current rate of the encoder.
     */
    public double getRate();

    /**
     * Set the distance per pulse for this encoder. This sets the multiplier
     * used to determine the distance driven based on the count value from the
     * encoder. Do not include the decoding type in this scale. The library
     * already compensates for the decoding type. Set this value based on the
     * encoder's rated Pulses per Revolution and factor in gearing reductions
     * following the encoder shaft. This distance can be in any units you like,
     * linear or angular.
     *
     * @param distancePerPulse The scale factor that will be used to convert
     * pulses to useful units.
     */
    public void setDistancePerPulse(double distancePerPulse);

    /**
     * Set the direction sensing for this encoder. This sets the direction
     * sensing on the encoder so that it could count in the correct software
     * direction regardless of the mounting.
     *
     * @param reverseDirection true if the encoder direction should be reversed
     */
    public void setReverseDirection(boolean reverseDirection);

    /**
     * {@inheritDoc}
     */
    public double pidGet();
}
