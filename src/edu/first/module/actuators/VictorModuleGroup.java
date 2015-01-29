package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;
import edu.first.module.subsystems.Subsystem;

/**
 * A group of victors. Performs actions on all of them. This group is a
 * {@link Subsystem}, meaning that it will perform the {@link Module} operations
 * on all of its elements.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class VictorModuleGroup extends Subsystem implements SpeedController {

    private final SpeedControllerGroup group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public VictorModuleGroup(VictorModule[] group) {
        super(group);
        this.group = new SpeedControllerGroup(group);
    }

    /**
     * Sets the speed of all the victors.
     *
     * @param speed speed to set
     * @see SpeedController#setSpeed(double)
     */
    @Override
    public void setSpeed(double speed) {
        group.setSpeed(speed);
    }

    /**
     * Sets the raw speed of all the victors.
     *
     * @param speed speed to set
     * @see SpeedController#setRawSpeed(int)
     */
    @Override
    public void setRawSpeed(int speed) {
        group.setRawSpeed(speed);
    }

    /**
     * Returns the speed of <i>every</i> controller. They must be in the same
     * state.
     *
     * @throws OutOfSyncException when all victors are not in the same state
     * @return speed of all the victors
     * @see SpeedController#getSpeed()
     */
    @Override
    public double getSpeed() throws OutOfSyncException {
        return group.getSpeed();
    }

    /**
     * Returns the raw speed of <i>every</i> controller. They must be in the
     * same state.
     *
     * @throws OutOfSyncException when all victors are not in the same state
     * @return speed of all the victors
     * @see SpeedController#getRawSpeed()
     */
    @Override
    public int getRawSpeed() throws OutOfSyncException {
        return group.getRawSpeed();
    }

    /**
     * Updates every controller.
     *
     * @see SpeedController#update()
     */
    @Override
    public void update() {
        group.update();
    }

    /**
     * Sets the rate of every controller.
     *
     * @param rate rate to set
     * @see SpeedController#setRate(double)
     */
    @Override
    public void setRate(double rate) {
        group.setRate(rate);
    }

    /**
     * Sets every controller.
     *
     * @param value speed to set
     * @see SpeedController#set(double)
     */
    @Override
    public void set(double value) {
        group.set(value);
    }

    /**
     * Returns the rate of <i>every</i> controller. They must be in the same
     * state.
     *
     * @throws OutOfSyncException when all victors are not in the same state
     * @return current rate of all victors
     * @see SpeedController#getRate()
     */
    @Override
    public double getRate() {
        return group.getRate();
    }

    /**
     * Returns the state of <i>every</i> controller. They must be in the same
     * state.
     *
     * @throws OutOfSyncException when all victors are not in the same state
     * @return state of all victors
     * @see SpeedController#get()
     */
    @Override
    public double get() {
        return group.get();
    }
}
