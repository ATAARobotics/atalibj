package edu.first.utils.preferences;

import edu.first.utils.Logger;

/**
 * Preference that holds a boolean value.
 *
 * @author Joel Gallant
 */
public final class BooleanPreference extends Preference {
    
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
        Logger.log(Logger.Urgency.USERMESSAGE, "Setting " + getKey() + " to " + value);
        PREFERENCES.putBoolean(getKey(), value);
    }

    /**
     * Returns the current value of the preference.
     *
     * @return value of preference
     */
    public boolean get() {
        return PREFERENCES.getBoolean(getKey(), defaultValue);
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
