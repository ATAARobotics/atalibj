package edu.ata.auto;

import edu.ata.user.UserInfo;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Singleton design pattern class that stores available autonomous modes and can
 * choose between them by name. Acts very similar to a {@link Hashtable},
 * because it stores data with one.
 *
 * <p> Stores one mode as the 'current' mode. This means that it is the
 * currently selected mode, otherwise known as the last mode to be passed
 * through
 * {@link AutonomousSelector#set(edu.ata.auto.AutonomousMode) set(AutonomousMode)}
 * or {@link AutonomousSelector#set(java.lang.String) set(String)}.
 *
 * @see Hashtable
 * @author Joel Gallant
 */
public final class AutonomousSelector {

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
        // Singleton instance
    }

    /**
     * Sets the autonomous mode to a previously entered mode, accessing it by
     * its name.
     *
     * @throws AutonomousModeNotSetException thrown when auto mode doesn't exist
     * @param name name of mode
     */
    public void set(String name) throws AutonomousModeNotSetException {
        if (!modes.containsKey(name)) {
            throw new AutonomousModeNotSetException("Set the autonomous mode to unavailable mode - " + name);
        }
        current = (AutonomousMode) modes.get(name);
    }

    /**
     * Sets the autonomous mode to a new autonomous mode object. If the object
     * is already stored, it will not be duplicated. (see
     * {@link Hashtable#put(java.lang.Object, java.lang.Object)})
     *
     * @param mode mode to set it to
     */
    public void set(AutonomousMode mode) {
        add(mode);
        set(mode.getName());
    }

    /**
     * Adds an autonomous mode to the available pool of modes, using its name in
     * the class as the accessor in
     * {@link AutonomousSelector#set(java.lang.String) set(String)}.
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
     * {@code set()}, a {@link AutonomousModeNotSetException} will be thrown.
     *
     * @throws AutonomousModeNotSetException thrown when autonomous is not set
     * @return currently selected autonomous mode
     */
    public AutonomousMode get() throws AutonomousModeNotSetException {
        if (current == null) {
            throw new AutonomousModeNotSetException("The autonomous mode was not set using AutonomousSelector.");
        }
        return current;
    }

    /**
     * Returns a specially formatted string that contains all the available
     * autonomous modes. Used by {@link UserInfo}.
     *
     * <p> Ex: Autonomous modes are ShootMiddle, DriveForwards and FeedBalls
     *
     * toString() returns:
     *
     * <pre> ShootMiddle,DriveForwards,FeedBalls </pre>
     *
     * @return formatted string with all modes, separated by commas
     */
    public String toString() {
        Enumeration e = modes.keys();
        String s = "";
        while (e.hasMoreElements()) {
            s += e.nextElement() + ",";
        }
        return s.length() > 0 ? s.substring(0, s.length() - 1) : "Empty";
    }

    /**
     * Exception meant to describe to the user that the autonomous mode was not,
     * or can not be set.
     */
    public class AutonomousModeNotSetException extends IllegalStateException {

        /**
         * Creates exception using a message. Has to include message to clarify
         * error.
         *
         * @param s error message
         */
        public AutonomousModeNotSetException(String s) {
            super(s);
        }
    }
}