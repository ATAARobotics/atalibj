package edu.first.module.sensor;

import edu.first.identifiers.ReturnableNumber;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Interface that represents gyro sensors. Is meant to describe all of their
 * functionality, as well as making them a {@link PIDSource}.
 *
 * @author Joel Gallant
 */
interface Gyro extends PIDSource, ReturnableNumber {

    /**
     * Returns the "angle", as it is registered on the gyroscope. There should
     * be no defined expectations of this method, unless specified by the
     * implementation.
     */
    public double getAngle();

    /**
     * Resets the gyro to a heading of zero.
     */
    public void reset();

    /**
     * {@inheritDoc}
     */
    public double pidGet();
}
