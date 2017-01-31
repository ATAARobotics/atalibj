package edu.first.util.dashboard;

import edu.first.identifiers.Input;
import edu.first.identifiers.Output;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * General class that interacts with number values on the {@link SmartDashboard}
 *
 * @since June 23 13
 * @author Joel Gallant
 */
public class NumberDashboard implements Input, Output {

    private final String key;
    private final double defaultValue;

    /**
     * Constructs the dashboard value with the key used to access it.
     *
     * @param key unique key that accesses the value
     * @param defaultValue value to return if none exists
     */
    public NumberDashboard(String key, double defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public NumberDashboard(int dashboardIndex, double defaultValue) {
        this("DB/Slider " + dashboardIndex, defaultValue);
    }

    /**
     * Returns the key of the dashboard value that is used to access it.
     *
     * @return key in smart dashboard
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns whether or not the value is currently stored on the CRIO.
     *
     * @return if value exists
     */
    public boolean exists() {
        try {
            SmartDashboard.getNumber(key, defaultValue);
            return true;
        } catch (TableKeyNotDefinedException ex) {
            return false;
        }
    }

    /**
     * Returns the current value associated with the key. Will return the
     * default value when it does not {@link #exists() exist}.
     *
     * @return current value
     */
    @Override
    public double get() {
        return SmartDashboard.getNumber(key, defaultValue);
    }

    /**
     * Sets the value to be associated with the key.
     *
     * @param val new value to set
     */
    @Override
    public void set(double val) {
        SmartDashboard.putNumber(key, val);
    }
}
