package edu.first.module.actuators;

import edu.first.module.Module;
import edu.first.util.Enum;

/**
 * Controls a dual-action solenoid. Has only two positions - left and right.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public class DualActionSolenoid extends Module.StartardModule {

    private final edu.wpi.first.wpilibj.Solenoid left;
    private final edu.wpi.first.wpilibj.Solenoid right;

    /**
     * Constructs the dual action solenoid using its two sides.
     *
     * @param left left side of he solenoid
     * @param right right side of the solenoid
     */
    public DualActionSolenoid(edu.wpi.first.wpilibj.Solenoid left,
            edu.wpi.first.wpilibj.Solenoid right) {
        if (left == null || right == null) {
            throw new NullPointerException("Null solenoid given");
        }
        this.left = left;
        this.right = right;
    }

    /**
     * Constructs the dual action solenoid using its two sides.
     *
     * @param leftChannel channel of the left side
     * @param rightChannel channel of the right side
     */
    public DualActionSolenoid(int leftChannel, int rightChannel) {
        this(new edu.wpi.first.wpilibj.Solenoid(leftChannel),
                new edu.wpi.first.wpilibj.Solenoid(rightChannel));
    }

    /**
     * Constructs the dual action solenoid using its two sides.
     *
     * @param leftChannel channel of the left side
     * @param leftSlot left channel's slot in cRIO (1 = default)
     * @param rightChannel channel of the right side
     * @param rightSlot right channel's slot in cRIO (1 = default)
     */
    public DualActionSolenoid(int leftChannel, int leftSlot, int rightChannel, int rightSlot) {
        this(new edu.wpi.first.wpilibj.Solenoid(leftSlot, leftChannel),
                new edu.wpi.first.wpilibj.Solenoid(rightSlot, rightChannel));
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p> Turns dual action off.
     */
    protected void disableModule() {
        set(Direction.OFF);
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * Sets the direction of the solenoid.
     *
     * @throws IllegalStateException when module is not enabled
     */
    public final void set(Direction direction) {
        ensureEnabled();
        if (direction == Direction.LEFT) {
            left.set(true);
            right.set(false);
        } else if (direction == Direction.RIGHT) {
            left.set(false);
            right.set(true);
        } else if (direction == Direction.OFF) {
            left.set(false);
            right.set(false);
        }
    }

    /**
     * Returns in which direction the solenoid is currently on. If both sides
     * are on, this method will return {@link Direction#OFF} because they will
     * have the same effect.
     *
     * @return current direction of solenoid
     */
    public final Direction get() {
        if (left.get() && !right.get()) {
            return Direction.LEFT;
        } else if (right.get() && !left.get()) {
            return Direction.RIGHT;
        } else {
            return Direction.OFF;
        }
    }

    /**
     * Reverses the direction of the solenoid. This means LEFT -> RIGHT or RIGHT
     * -> LEFT.
     */
    public final void reverse() {
        if (get() == Direction.LEFT) {
            set(Direction.RIGHT);
        } else if (get() == Direction.RIGHT) {
            set(Direction.LEFT);
        }
    }

    /**
     * Turns both ends of the solenoid off.
     */
    public final void turnOff() {
        set(Direction.OFF);
    }

    /**
     * Enum representing the directions of a dual action solenoid.
     */
    public static final class Direction extends Enum {

        /**
         * The left side of the solenoid is on.
         */
        public static final Direction LEFT = new Direction("LEFT");
        /**
         * The right side of the solenoid is on.
         */
        public static final Direction RIGHT = new Direction("RIGHT");
        /**
         * Both solenoids are off.
         */
        public static final Direction OFF = new Direction("OFF");

        private Direction(String name) {
            super(name);
        }
    }
}
