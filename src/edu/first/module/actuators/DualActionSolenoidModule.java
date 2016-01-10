package edu.first.module.actuators;

import edu.first.command.Command;
import edu.first.module.Module;

/**
 * Controls a dual-action solenoid. Has only two positions - left and right.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public class DualActionSolenoidModule extends Module.StandardModule implements DualActionSolenoid {

    private final edu.wpi.first.wpilibj.Solenoid left;
    private final edu.wpi.first.wpilibj.Solenoid right;

    /**
     * Constructs the dual action solenoid using its two sides.
     *
     * @throws NullPointerException when left or right solenoids are null
     * @param left left side of he solenoid
     * @param right right side of the solenoid
     */
    protected DualActionSolenoidModule(edu.wpi.first.wpilibj.Solenoid left,
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
    public DualActionSolenoidModule(int leftChannel, int rightChannel) {
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
    public DualActionSolenoidModule(int leftChannel, int leftSlot, int rightChannel, int rightSlot) {
        this(new edu.wpi.first.wpilibj.Solenoid(leftSlot, leftChannel),
                new edu.wpi.first.wpilibj.Solenoid(rightSlot, rightChannel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Turns dual action off.
     */
    @Override
    protected void disableModule() {
        left.set(false);
        right.set(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void set(Direction direction) {
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
     * Returns a command that calls
     * {@link #set(edu.first.module.actuators.DualActionSolenoid.Direction)}.
     *
     * @param direction which direction to set the solenoid
     * @return setting command
     */
    public Command setCommand(final Direction direction) {
        return new Command() {
            @Override
            public void run() {
                set(direction);
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public Direction get() {
        ensureEnabled();
        if (left.get() && !right.get()) {
            return Direction.LEFT;
        } else if (right.get() && !left.get()) {
            return Direction.RIGHT;
        } else {
            return Direction.OFF;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void reverse() {
        ensureEnabled();
        if (get() == Direction.LEFT) {
            set(Direction.RIGHT);
        } else if (get() == Direction.RIGHT) {
            set(Direction.LEFT);
        }
    }

    /**
     * Returns a command that calls {@link #reverse()}.
     *
     * @return reversing command
     */
    public Command reverseCommand() {
        return new Command() {
            @Override
            public void run() {
                reverse();
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void turnOff() {
        ensureEnabled();
        set(Direction.OFF);
    }
}
