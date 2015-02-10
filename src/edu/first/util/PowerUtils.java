package edu.first.util;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * Utilities to monitor power usage, with the new PDP.
 *
 * @since Jan 30 15
 * @author Joel Gallant
 */
public final class PowerUtils {

    private static final PowerDistributionPanel panel = new PowerDistributionPanel();

    // cannot be subclassed or instantiated
    private PowerUtils() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Query the current of a single channel of the PDP
     *
     * @param channel channel to monitor
     * @return The current of one of the PDP channels (channels 0-15) in Amperes
     */
    public static double getCurrent(int channel) {
        return panel.getCurrent(channel);
    }

    /**
     * Query the input voltage of the PDP
     *
     * @return The voltage of the PDP in volts
     */
    public static double getVoltage() {
        return panel.getVoltage();
    }

    /**
     * Query the current of all monitored PDP channels (0-15)
     *
     * @return The current of all the channels in Amperes
     */
    public static double getTotalCurrent() {
        return panel.getTotalCurrent();
    }

    /**
     * Query the temperature of the PDP
     *
     * @return The temperature of the PDP in degrees Celsius
     */
    public static double getTemperature() {
        return panel.getTemperature();
    }

    /**
     * Query the total power drawn from the monitored PDP channels
     *
     * @return the total power in Watts
     */
    public static double getTotalPower() {
        return panel.getTotalPower();
    }

    /**
     * Query the total energy drawn from the monitored PDP channels
     *
     * @return the total energy in Joules
     */
    public static double getTotalEnergy() {
        return panel.getTotalEnergy();
    }
}
