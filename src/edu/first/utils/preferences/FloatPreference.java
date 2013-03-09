package edu.first.utils.preferences;

/**
 * Preference that holds a float value.
 *
 * @author Joel Gallant
 */
public final class FloatPreference extends Preference {

    private final float defaultValue;

    /**
     * Constructs the preference with its key used to access it in
     * {@link Preferences}.
     *
     * @param key string to access preference
     * @param defaultValue value to set if none exist
     */
    public FloatPreference(String key, float defaultValue) {
        super(key);
        this.defaultValue = defaultValue;
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param value new value of preference
     */
    public void set(float value) {
        PREFERENCES.putFloat(getKey(), value);
    }

    /**
     * Returns the current value of the preference.
     *
     * @return value of preference
     */
    public float get() {
        return PREFERENCES.getFloat(getKey(), defaultValue);
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
