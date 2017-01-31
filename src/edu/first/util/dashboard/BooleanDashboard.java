package edu.first.util.dashboard;

import edu.first.identifiers.Position;
import edu.first.identifiers.Switch;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * General class that interacts with boolean values on the
 * {@link SmartDashboard}.
 *
 * @since June 23 13
 * @author Joel Gallant
 */
public class BooleanDashboard implements Switch, Position {

    private final String key;
    private final boolean defaultValue;

    /**
     * Constructs the dashboard value with the key used to access it.
     *
     * @param key unique key that accesses the value
     * @param defaultValue value to return if none exists
     */
    public BooleanDashboard(String key, boolean defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public BooleanDashboard(int dashboardIndex, boolean defaultValue) {
        this("DB/Button " + dashboardIndex, defaultValue);
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
            SmartDashboard.getBoolean(key, defaultValue);
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
    public boolean get() {
        return SmartDashboard.getBoolean(key, defaultValue);
    }

    /**
     * Sets the value to be associated with the key.
     *
     * @param val new value to set
     */
    public void set(boolean val) {
        SmartDashboard.putBoolean(key, val);
    }

    /**
     * Sets the value to be associated with the key.
     *
     * @param pos new value to set
     */
    @Override
    public void setPosition(boolean pos) {
        SmartDashboard.putBoolean(key, pos);
    }

    /**
     * Returns the current value associated with the key. Will return the
     * default value when it does not {@link #exists() exist}.
     *
     * @return current value
     */
    @Override
    public boolean getPosition() {
        return SmartDashboard.getBoolean(key, defaultValue);
    }
}
