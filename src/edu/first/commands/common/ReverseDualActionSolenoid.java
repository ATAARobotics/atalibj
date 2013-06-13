package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.actuators.DualActionSolenoid;

/**
 * Reverses the direction of the solenoid. This means LEFT -> RIGHT or RIGHT ->
 * LEFT.
 *
 * @see DualActionSolenoid#reverse()
 * @since June 13 13
 * @author Joel Gallant
 */
public final class ReverseDualActionSolenoid implements Command {

    private final DualActionSolenoid solenoid;

    /**
     * Constructs the command using the solenoid to change.
     *
     * @param solenoid solenoid to reverse
     */
    public ReverseDualActionSolenoid(DualActionSolenoid solenoid) {
        this.solenoid = solenoid;
    }

    /**
     * Reverses the direction of the solenoid. This means LEFT -> RIGHT or RIGHT
     * -> LEFT.
     */
    public void run() {
        solenoid.reverse();
    }
}
