package edu.first.module.sensor;

import edu.first.identifiers.ReturnableNumber;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Interface to represent accelerometer sensors.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Accelerometer extends PIDSource, ReturnableNumber {

    /**
     * Returns the acceleration in G's.
     *
     * @param axes which axis to measure
     * @return acceleration measured by sensor
     */
    public double getAcceleration(ADXL345_I2C.Axes axes);
}
