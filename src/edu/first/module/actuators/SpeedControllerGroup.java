package edu.first.module.actuators;

import edu.first.util.list.Iterator;
import edu.first.util.list.ArrayList;

/**
 * A group for managing a set of {@code SpeedController Speed Controllers} as
 * one single
 *
 * @since May 30 13
 * @author Aaron Weiss
 * @author Joel Gallant
 */
public class SpeedControllerGroup implements SpeedController {

    private final ArrayList controllers = new ArrayList();

    /**
     * Creates a new, empty {@code SpeedControllerGroup}.
     */
    public SpeedControllerGroup() {
    }

    /**
     * Creates a new {@code SpeedControllerGroup} with a single starting
     * {@code controller}.
     *
     * @param controller the speed controller to start with
     */
    public SpeedControllerGroup(SpeedController controller) {
        add(controller);
    }

    /**
     * Creates a new {@code SpeedControllerGroup} starting with an array of
     * {@code controllers}.
     *
     * @param controllers the speed controllers to start with
     */
    public SpeedControllerGroup(SpeedController[] controllers) {
        addAll(controllers);
    }

    /**
     * Adds a {@code SpeedController} to the {@code SpeedControllerGroup}.
     *
     * @param controller the speed controller to add
     */
    public final SpeedControllerGroup add(SpeedController controller) {
        if (controller == null) {
            throw new NullPointerException("Null controller given");
        }
        controllers.add(controller);
        return this;
    }

    /**
     * Adds an array of {@code SpeedController Speed Controllers} to the
     * {@code SpeedControllerGroup}.
     *
     * @param controllers the speed controller to add
     */
    public final SpeedControllerGroup addAll(SpeedController[] controllers) {
        for (int x = 0; x < controllers.length; x++) {
            add(controllers[x]);
        }
        return this;
    }

    /**
     * Removes the {@code SpeedController} at the desired index.
     *
     * @param index the index to remove
     */
    public final void remove(int index) {
        controllers.remove(index);
    }

    /**
     * Removes the desired {@code SpeedController} from the group.
     *
     * @param controller the speed controller to remove
     */
    public final void remove(SpeedController controller) {
        controllers.remove(controller);
    }

    /**
     * Returns the amount of speed controllers that are being used in this
     * group.
     *
     * @return how many controllers are a part of the group
     */
    public final int controllers() {
        return controllers.size();
    }

    /**
     * {@inheritDoc}
     */
    public void setSpeed(double speed) {
        final Iterator iter = controllers.iterator();
        while (iter.hasNext()) {
            ((SpeedController) iter.next()).setSpeed(speed);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setRawSpeed(int speed) {
        final Iterator iter = controllers.iterator();
        while (iter.hasNext()) {
            ((SpeedController) iter.next()).setRawSpeed(speed);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double getSpeed() {
        if (controllers.size() > 0) {
            return ((SpeedController) controllers.get(0)).getSpeed();
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getRawSpeed() {
        if (controllers.size() > 0) {
            return ((SpeedController) controllers.get(0)).getRawSpeed();
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        final Iterator iter = controllers.iterator();
        while (iter.hasNext()) {
            ((SpeedController) iter.next()).update();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setRate(double rate) {
        final Iterator iter = controllers.iterator();
        while (iter.hasNext()) {
            ((SpeedController) iter.next()).setRate(rate);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void set(double value) {
        final Iterator iter = controllers.iterator();
        while (iter.hasNext()) {
            ((SpeedController) iter.next()).set(value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double getRate() {
        if (controllers.size() > 0) {
            return ((SpeedController) controllers.get(0)).getRate();
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    public double get() {
        if (controllers.size() > 0) {
            return ((SpeedController) controllers.get(0)).get();
        } else {
            return 0;
        }
    }
}
