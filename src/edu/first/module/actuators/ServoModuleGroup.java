package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;
import edu.first.module.subsystems.Subsystem;

/**
 * A group of servos. Performs actions on all of them. This group is a
 * {@link Subsystem}, meaning that it will perform the {@link Module} operations
 * on all of its elements.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class ServoModuleGroup extends Subsystem implements Servo {

    private final ServoGroup group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public ServoModuleGroup(ServoModule[] group) {
        super(group);
        this.group = new ServoGroup(group);
    }

    /**
     * Sets the position of every servo.
     *
     * @param position position to set
     * @see Servo#setPosition(double)
     */
    @Override
    public void setPosition(double position) {
        group.setPosition(position);
    }

    /**
     * Sets every servo.
     *
     * @param position position to set
     * @see Servo#set(double)
     */
    @Override
    public void set(double position) {
        group.set(position);
    }

    /**
     * Returns the position that <i>every</i> servo is in.
     *
     * @throws OutOfSyncException when all controllers are not in the same state
     * @return position of all servos
     * @see Servo#getPosition()
     */
    @Override
    public double getPosition() throws OutOfSyncException {
        return group.getPosition();
    }

    /**
     * Returns the state that <i>every</i> servo is in.
     *
     * @throws OutOfSyncException when all controllers are not in the same state
     * @return state of all servos
     * @see Servo#get()
     */
    @Override
    public double get() throws OutOfSyncException {
        return group.get();
    }
}
