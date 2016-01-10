package edu.first.util.preferences;

import edu.first.identifiers.Output;
import edu.first.util.log.Logger;
import edu.wpi.first.wpilibj.Preferences;

/**
 * Preference that holds a long value.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public final class LongPreference extends Preference implements Output {

    private final long defaultValue;

    /**
     * Constructs the preference with its key used to access it in
     * {@link Preferences}.
     *
     * @param key string to access preference
     * @param defaultValue value to use if none exist
     */
    public LongPreference(String key, long defaultValue) {
        super(key);
        this.defaultValue = defaultValue;
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param value new value of preference
     */
    public void set(long value) {
        Logger.getLogger(getClass()).debug("Setting " + getKey() + " to " + value);
        PREFERENCES.putLong(getKey(), value);
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param value new value of preference
     */
    @Override
    public void set(double value) {
        set((long) value);
    }

    /**
     * Returns the current value of the preference.
     *
     * @return value of preference
     */
    public long get() {
        return PREFERENCES.getLong(getKey(), defaultValue);
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
