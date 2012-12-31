package edu.ATA.module.sensor;

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.Gyro}.
 *
 * @author Joel Gallant
 */
class ForwardingGyro implements Gyro {

    private final edu.wpi.first.wpilibj.Gyro gyro;

    /**
     * Constructs the object by using composition, using the given gyro object
     * to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    ForwardingGyro(edu.wpi.first.wpilibj.Gyro gyro) {
        this.gyro = gyro;
    }

    /**
     *
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.Gyro}.
     *
     * @return composition object under this one
     */
    protected edu.wpi.first.wpilibj.Gyro getGyro() {
        return gyro;
    }

    /**
     * {@inheritDoc}
     */
    public double getAngle() {
        return gyro.getAngle();
    }

    /**
     * {@inheritDoc}
     */
    public void reset() {
        gyro.reset();
    }

    /**
     * {@inheritDoc}
     */
    public double pidGet() {
        return gyro.pidGet();
    }
}
