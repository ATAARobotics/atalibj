package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;

/**
 * A group of speed controllers. Performs actions on all controllers.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class SpeedControllerGroup implements SpeedController {

    private final SpeedController[] group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public SpeedControllerGroup(SpeedController[] group) {
        if (group == null) {
            throw new NullPointerException("Group provided was null");
        }
        this.group = group;
    }

    /**
     * Sets the speed of all the speed controllers.
     *
     * @param speed speed to set
     * @see SpeedController#setSpeed(double)
     */
    public void setSpeed(double speed) {
        for (int x = 0; x < group.length; x++) {
            group[x].setSpeed(speed);
        }
    }

    /**
     * Sets the raw speed of all the speed controllers.
     *
     * @param speed speed to set
     * @see SpeedController#setRawSpeed(int)
     */
    public void setRawSpeed(int speed) {
        for (int x = 0; x < group.length; x++) {
            group[x].setRawSpeed(speed);
        }
    }

    /**
     * Returns the speed of <i>every</i> controller. They must be in the same
     * state.
     *
     * @throws OutOfSyncException when all controllers are not in the same state
     * @return speed of all the controllers
     * @see SpeedController#getSpeed()
     */
    public double getSpeed() {
        double speed = 0;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                speed = group[x].getSpeed();
            } else {
                double b = group[x].getSpeed();
                if (b != speed) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + speed + ")");
                }
            }
        }
        return speed;
    }

    /**
     * Returns the raw speed of <i>every</i> controller. They must be in the
     * same state.
     *
     * @throws OutOfSyncException when all controllers are not in the same state
     * @return speed of all the controllers
     * @see SpeedController#getRawSpeed()
     */
    public int getRawSpeed() {
        int speed = 0;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                speed = group[x].getRawSpeed();
            } else {
                int b = group[x].getRawSpeed();
                if (b != speed) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + speed + ")");
                }
            }
        }
        return speed;
    }

    /**
     * Updates every controller.
     *
     * @see SpeedController#update()
     */
    public void update() {
        for (int x = 0; x < group.length; x++) {
            group[x].update();
        }
    }

    /**
     * Sets the rate of every controller.
     *
     * @param rate rate to set
     * @see SpeedController#setRate(double)
     */
    public void setRate(double rate) {
        for (int x = 0; x < group.length; x++) {
            group[x].setRate(rate);
        }
    }

    /**
     * Sets every controller.
     *
     * @param value speed to set
     * @see SpeedController#set(double)
     */
    public void set(double value) {
        for (int x = 0; x < group.length; x++) {
            group[x].set(value);
        }
    }

    /**
     * Returns the rate of <i>every</i> controller. They must be in the same
     * state.
     *
     * @throws OutOfSyncException when all controllers are not in the same state
     * @return current rate of all controllers
     * @see SpeedController#getRate()
     */
    public double getRate() {
        double rate = 0;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                rate = group[x].getRate();
            } else {
                double b = group[x].getRate();
                if (b != rate) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + rate + ")");
                }
            }
        }
        return rate;
    }

    /**
     * Returns the state of <i>every</i> controller. They must be in the same
     * state.
     *
     * @throws OutOfSyncException when all controllers are not in the same state
     * @return state of all controllers
     * @see SpeedController#get()
     */
    public double get() {
        double speed = 0;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                speed = group[x].get();
            } else {
                double b = group[x].get();
                if (b != speed) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + speed + ")");
                }
            }
        }
        return speed;
    }
}
