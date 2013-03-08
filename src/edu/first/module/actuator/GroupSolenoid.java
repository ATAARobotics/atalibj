package edu.first.module.actuator;

/**
 * Solenoid that controls multiple solenoids, doing the same thing to all of
 * them.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GroupSolenoid implements Solenoid {

    private final Solenoid[] solenoids;

    /**
     * Constructs the group solenoid with an array of all the solenoids to use.
     * There must be at least one solenoid in the array.
     * ({@link IllegalStateException})
     *
     * @param solenoids solenoids to control
     */
    public GroupSolenoid(Solenoid[] solenoids) {
        if (solenoids == null || solenoids.length == 0) {
            throw new IllegalStateException("GroupSolenoid with no solenoids");
        }
        this.solenoids = solenoids;
    }

    /**
     * Sets all the solenoids to a state.
     *
     * @param on state of solenoids
     */
    public void set(boolean on) {
        for (int x = 0; x < solenoids.length; x++) {
            solenoids[x].set(on);
        }
    }

    /**
     * Returns the state of all the solenoids. If one of the solenoids is not
     * the same state as the others, an {@link IllegalStateException} is thrown.
     *
     * @return state of all solenoids
     */
    public boolean get() {
        boolean get = solenoids[0].get();
        for (int x = 1; x < solenoids.length; x++) {
            if (solenoids[x].get() != get) {
                throw new IllegalStateException("GroupSolenoid not in sync.");
            }
        }
        return get;
    }
}