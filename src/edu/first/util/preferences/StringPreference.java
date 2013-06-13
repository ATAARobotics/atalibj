package edu.first.util.preferences;

import edu.first.util.log.Logger;
import edu.wpi.first.wpilibj.Preferences;

/**
 * Preference that holds a string value.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public final class StringPreference extends Preference {

    private final String defaultValue;

    /**
     * Constructs the preference with its key used to access it in
     * {@link Preferences}.
     *
     * @param key string to access preference
     * @param defaultValue value to use if none exist
     */
    public StringPreference(String key, String defaultValue) {
        super(key);
        this.defaultValue = defaultValue;
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param value new value of preference
     */
    public void set(String value) {
        Logger.getLogger(getClass()).debug("Setting " + getKey() + " to " + value);
        PREFERENCES.putString(getKey(), value);
    }

    /**
     * Returns the current value of the preference.
     *
     * @return value of preference
     */
    public String get() {
        return PREFERENCES.getString(getKey(), defaultValue);
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
