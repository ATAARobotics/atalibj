package edu.ata.preferences;

import edu.first.utils.Logger;
import edu.first.utils.preferences.DoublePreference;

/**
 * Preference that stores an RPM and default speed value.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class RPMPreference {

    private final DoublePreference rpm;
    private final DoublePreference defaultSpeed;

    private static double convertToDefaultSpeed(double rpm) {
        double defaultSpeed;
        if (rpm < 2500) {
            defaultSpeed = 0;
        } else if (rpm < 3000) {
            defaultSpeed = 0.4;
        } else if (rpm < 3500) {
            defaultSpeed = 0.5;
        } else if (rpm < 4000) {
            defaultSpeed = 0.6;
        } else if (rpm < 4500) {
            defaultSpeed = 0.7;
        } else if (rpm < 5000) {
            defaultSpeed = 0.8;
        } else {
            defaultSpeed = 0.9;
        }
        return defaultSpeed;
    }

    /**
     * Constructs the preference.
     *
     * @param name string to put in front of "RPM" and "Default Speed
     * @param rpm default RPM
     */
    public RPMPreference(String name, double rpm) {
        this.rpm = new DoublePreference(name + "RPM", rpm);
        this.defaultSpeed = new DoublePreference(name + "DefaultSpeed", convertToDefaultSpeed(rpm));
    }

    /**
     * Sets the value of the preference. Will not save over reboot.
     *
     * @param value new value of preference
     */
    public void set(double value) {
        rpm.set(value);
        defaultSpeed.set(convertToDefaultSpeed(value));
        Logger.log(Logger.Urgency.USERMESSAGE, "RPM - " + (int) rpm.get());
        Logger.log(Logger.Urgency.USERMESSAGE, "DefSpeed - " + defaultSpeed.get());
    }

    /**
     * Returns the preference that contains the RPM.
     *
     * @return preference with RPM
     */
    public DoublePreference getRPMPreference() {
        return rpm;
    }

    /**
     * Returns the current value of the RPM.
     *
     * @return value of preference
     */
    public double getRPM() {
        return rpm.get();
    }

    /**
     * Returns the current value of the default speed.
     *
     * @return value of preference
     */
    public double getDefaultSpeed() {
        return defaultSpeed.get();
    }

    /**
     * If the preference does not exist ({@link Preference#exists()}), creates
     * it with the default value.
     *
     * @param initialValue value to set to
     */
    public void create() {
        rpm.create();
        defaultSpeed.create();
    }
}