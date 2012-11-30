package edu.ata.auto;

import edu.ata.user.UserInfo;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Singleton design pattern class that stores available autonomous modes and can
 * choose between them by name.
 *
 * @author Joel Gallant
 */
public class AutonomousSelector {

    private static AutonomousSelector instance;

    /**
     * Returns the singleton instance of the autonomous selector, using a
     * synchronized check to ensure that it never gets half-written / accessed
     * too early.
     *
     * @return singleton instance of selector
     */
    public static AutonomousSelector getInstance() {
        synchronized (AutonomousSelector.class) {
            if (instance == null) {
                instance = new AutonomousSelector();
            }
        }
        return instance;
    }
    private AutonomousMode current;
    private final Hashtable modes = new Hashtable();

    private AutonomousSelector() {
    }

    /**
     * Sets the autonomous mode to a previously entered mode, accessing by its
     * name.
     *
     * @param name name of mode
     */
    public void set(String name) {
        current = (AutonomousMode) modes.get(name);
    }

    /**
     * Sets the autonomous mode to a new autonomous mode object.
     *
     * @param mode mode to set it to
     */
    public void set(AutonomousMode mode) {
        add(mode);
        set(mode.getName());
    }

    /**
     * Adds an autonomous mode to the available pool of modes, using its name in
     * the class.
     *
     * @param mode mode to add
     */
    public void add(AutonomousMode mode) {
        modes.put(mode.getName(), mode);
    }

    /**
     * Removes an autonomous mode by its name from the pool of modes.
     *
     * @param name name of mode to remove
     */
    public void remove(String name) {
        modes.remove(name);
    }

    /**
     * Gets the currently selected autonomous mode. If one has not been set with
     * {@code set()}, this method will return {@code null}.
     *
     * @return currently selected autonomous mode
     */
    public AutonomousMode get() {
        return current;
    }

    /**
     * Returns a specially formatted string that contains all the available
     * autonomous modes. Used by {@link UserInfo}.
     *
     * @return formatted string with all modes, separated by commas
     */
    public String toString() {
        Enumeration e = modes.keys();
        String s = "";
        while (e.hasMoreElements()) {
            s += e.nextElement() + ",";
        }
        return s;
    }
}