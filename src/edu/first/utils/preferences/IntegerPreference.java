package edu.first.utils.preferences;

import edu.first.utils.Logger;

/**
 * Preference that holds an integer value.
 *
 * @author Joel Gallant
 */
public final class IntegerPreference extends Preference {

    private final int defaultValue;

    /**
     * Constructs the preference with its key used to access it in
     * {@link Preferences}.
     *
     * @param key string to access preference
     * @param defaultValue value to set if none exist
     */
    public IntegerPreference(String key, int defaultValue) {
        super(key);
        this.defaultValue = defaultValue;
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param value new value of preference
     */
    public void set(int value) {
        Logger.log(Logger.Urgency.LOG, "Setting " + getKey() + " to " + value);
        PREFERENCES.putInt(getKey(), value);
    }

    /**
     * Returns the current value of the preference.
     *
     * @return value of preference
     */
    public int get() {
        return PREFERENCES.getInt(getKey(), defaultValue);
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
