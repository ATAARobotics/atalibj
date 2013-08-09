package edu.first.util.preferences;

import edu.wpi.first.wpilibj.Preferences;

/**
 * General class to represent preferences that interact with
 * {@link Preferences}. Only atalibj classes should extend this one.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public class Preference {

    /**
     * Static singleton instance of {@link Preferences}.
     */
    protected static final Preferences PREFERENCES = Preferences.getInstance();
    private final String key;

    Preference(String key) {
        this.key = key;
    }

    /**
     * Returns the key being used to reference the preference.
     *
     * @return key in preferences
     */
    public final String getKey() {
        return key;
    }

    /**
     * Returns whether or not the preference is currently stored on the CRIO.
     *
     * @return if preference exists
     */
    public final boolean exists() {
        return PREFERENCES.containsKey(key);
    }
}
