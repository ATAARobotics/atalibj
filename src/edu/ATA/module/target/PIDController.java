package edu.ATA.module.target;

/**
 * Interface that represents full functionality of a PID controller (see
 * http://www.chiefdelphi.com/forums/showthread.php?threadid=110268). Is mostly
 * designed to control a {@link edu.wpi.first.wpilibj.PIDController} object.
 *
 * @author Joel Gallant
 */
interface PIDController {

    /**
     * Set the PID Controller gain parameters. Set the proportional, integral,
     * and differential coefficients.
     *
     * @param p proportional coefficient
     * @param i integral coefficient
     * @param d differential coefficient
     */
    public void setPID(double p, double i, double d);

    /**
     * Set the PID Controller feel forward coefficient.
     *
     * @param f feed forward coefficient
     */
    public void setF(double f);

    /**
     * Get the Proportional coefficient.
     *
     * @return proportional coefficient
     */
    public double getP();

    /**
     * Get the Integral coefficient.
     *
     * @return integral coefficient
     */
    public double getI();

    /**
     * Get the Differential coefficient.
     *
     * @return differential coefficient
     */
    public double getD();

    /**
     * Get the Feed forward coefficient.
     *
     * @return feed forward coefficient
     */
    public double getF();

    /**
     * Sets the maximum and minimum values expected from the input.
     *
     * @param minimumInput the minimum percentage expected from the input
     * @param maximumInput the maximum percentage expected from the output
     */
    public void setInputRange(double minimumInput, double maximumInput);

    /**
     * Sets the minimum and maximum values to write.
     *
     * @param minimumOutput the minimum percentage to write to the output
     * @param maximumOutput the maximum percentage to write to the output
     */
    public void setOutputRange(double minimumOutput, double maximumOutput);

    /**
     * Set the setpoint for the PIDController.
     *
     * @param setpoint the desired setpoint
     */
    public void setSetpoint(double setpoint);

    /**
     * Returns the current setpoint of the PIDController.
     *
     * @return the current setpoint
     */
    public double getSetpoint();

    /**
     * Return true if the error is within the percentage of the total input
     * range, determined by setTolerance.
     *
     * @return true if the error is less than the tolerance
     */
    public boolean onTarget();

    /**
     * Set the percentage error which is considered tolerable for use with
     * OnTarget. (Input of 15.0 = 15 percent)
     *
     * @param percent error which is tolerable
     */
    public void setPercentTolerance(double percentage);
}
