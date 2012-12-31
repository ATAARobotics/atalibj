package edu.ATA.module.sensor;

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.Encoder}.
 *
 * @author Joel Gallant
 */
class ForwardingEncoder implements Encoder {

    private final edu.wpi.first.wpilibj.Encoder encoder;

    /**
     * Constructs the object by using composition, using the given encoder
     * object to control methods in this class.
     *
     * @param encoder actual underlying object used
     */
    ForwardingEncoder(edu.wpi.first.wpilibj.Encoder encoder, edu.wpi.first.wpilibj.Encoder.PIDSourceParameter pidSource) {
        this.encoder = encoder;
        encoder.setPIDSourceParameter(pidSource);
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.Encoder}.
     *
     * @return composition object under this one
     */
    protected edu.wpi.first.wpilibj.Encoder getEncoder() {
        return encoder;
    }

    /**
     * {@inheritDoc}
     */
    public void start() {
        encoder.start();
    }

    /**
     * {@inheritDoc}
     */
    public void stop() {
        encoder.stop();
    }

    /**
     * {@inheritDoc}
     */
    public void reset() {
        encoder.reset();
    }

    /**
     * {@inheritDoc}
     */
    public void setMaxPeriod(double maxPeriod) {
        encoder.setMaxPeriod(maxPeriod);
    }

    /**
     * {@inheritDoc}
     */
    public boolean getStopped() {
        return encoder.getStopped();
    }

    /**
     * {@inheritDoc}
     */
    public double getDistance() {
        return encoder.getDistance();
    }

    /**
     * {@inheritDoc}
     */
    public double getRate() {
        return encoder.getRate();
    }

    /**
     * {@inheritDoc}
     */
    public void setReverseDirection(boolean reverseDirection) {
        encoder.setReverseDirection(reverseDirection);
    }

    /**
     * {@inheritDoc}
     */
    public double pidGet() {
        return encoder.pidGet();
    }

    /**
     * {@inheritDoc}
     */
    public void setDistancePerPulse(double distancePerPulse) {
        encoder.setDistancePerPulse(distancePerPulse);
    }
}
