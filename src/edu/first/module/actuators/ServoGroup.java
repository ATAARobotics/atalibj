package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;

/**
 * A group of servos. Performs actions on all of them.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class ServoGroup implements Servo {

    private final Servo[] group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public ServoGroup(Servo[] group) {
        if(group == null) {
            throw new NullPointerException("Null group given");
        }
        this.group = group;
    }

    /**
     * Sets the position of every servo.
     *
     * @param position position to set
     * @see Servo#setPosition(double)
     */
    public void setPosition(double position) {
        for (int x = 0; x < group.length; x++) {
            group[x].setPosition(position);
        }
    }

    /**
     * Sets every servo.
     *
     * @param position position to set
     * @see Servo#set(double)
     */
    public void set(double position) {
        for (int x = 0; x < group.length; x++) {
            group[x].set(position);
        }
    }

    /**
     * Returns the position that <i>every</i> servo is in.
     *
     * @throws OutOfSyncException when all servos are not in the same state
     * @return position of all servos
     * @see Servo#getPosition()
     */
    public double getPosition() {
        double pos = 0;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                pos = group[x].getPosition();
            } else {
                double b = group[x].getPosition();
                if (b != pos) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + pos + ")");
                }
            }
        }
        return pos;
    }

    /**
     * Returns the state that <i>every</i> servo is in.
     *
     * @throws OutOfSyncException when all servos are not in the same state
     * @return state of all servos
     * @see Servo#get()
     */
    public double get() {
        double pos = 0;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                pos = group[x].get();
            } else {
                double b = group[x].get();
                if (b != pos) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + pos + ")");
                }
            }
        }
        return pos;
    }
}
