package edu.first.util.dashboard;

import edu.first.identifiers.StringInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * General class that interacts with string values on the
 * {@link SmartDashboard}.
 *
 * @since June 23 13
 * @author Joel Gallant
 */
public class StringDashboard implements StringInput {

    private final String key;
    private final String defaultValue;

    /**
     * Constructs the dashboard value with the key used to access it.
     *
     * @param key unique key that accesses the value
     * @param defaultValue value to return if none exists
     */
    public StringDashboard(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public StringDashboard(int dashboardIndex, String defaultValue) {
        this("DB/String " + dashboardIndex, defaultValue);
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
            SmartDashboard.getString(key, defaultValue);
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
    public String get() {
        return SmartDashboard.getString(key, defaultValue);
    }

    /**
     * Returns the current value associated with the key. Will return the
     * default value when it does not {@link #exists() exist}.
     *
     * @return current value
     */
    @Override
    public String getValue() {
        return SmartDashboard.getString(key, defaultValue);
    }

    /**
     * Sets the value to be associated with the key.
     *
     * @param val new value to set
     */
    public void set(String val) {
        SmartDashboard.putString(key, val);
    }
}
