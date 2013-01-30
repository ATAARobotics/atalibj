package edu.ATA.module.sensor;

public interface HallEffect {

    /**
     * Returns the current timer value.
     *
     * @return the current counter value
     */
    int getCount();

    /**
     * Returns the counter rate.
     *
     * @return the current counter rate
     */
    double getRate();

    /**
     * Returns boolean polarity of the Hall Effect sensor.
     *
     * @return the Hall Effect sensor polarity
     */
    boolean isPolarized();
}