package edu.first.util.preferences;

import edu.first.identifiers.Position;
import edu.first.identifiers.Switch;
import edu.first.util.log.Logger;
import edu.wpi.first.wpilibj.Preferences;

/**
 * Preference that holds a boolean value.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public final class BooleanPreference extends Preference implements Position, Switch {

    private final boolean defaultValue;

    /**
     * Constructs the preference with its key used to access it in
     * {@link Preferences}.
     *
     * @param key string to access preference
     * @param defaultValue value to use when none exists
     */
    public BooleanPreference(String key, boolean defaultValue) {
        super(key);
        this.defaultValue = defaultValue;
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param value new value of preference
     */
    public void set(boolean value) {
        Logger.getLogger(getClass()).debug("Setting " + getKey() + " to " + value);
        PREFERENCES.putBoolean(getKey(), value);
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param pos new value of preference
     */
    @Override
    public void setPosition(boolean pos) {
        set(pos);
    }

    /**
     * Returns the current value of the preference.
     *
     * @return value of preference
     */
    public boolean get() {
        return PREFERENCES.getBoolean(getKey(), defaultValue);
    }

    @Override
    public boolean getPosition() {
        return get();
    }

    /**
     * If the preference does not exist ({@link Preference#exists()}), creates
     * it with the default value.
     */
    public void create() {
        if (!exists()) {
            set(defaultValue);
        }
    }
}
