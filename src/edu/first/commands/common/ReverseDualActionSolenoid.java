package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.actuators.DualActionSolenoidModule;

/**
 * Reverses the direction of the solenoid. This means LEFT -> RIGHT or RIGHT ->
 * LEFT.
 *
 * @see DualActionSolenoid#reverse()
 * @since June 13 13
 * @author Joel Gallant
 */
public final class ReverseDualActionSolenoid implements Command {

    private final DualActionSolenoidModule solenoid;

    /**
     * Constructs the command using the solenoid to change.
     *
     * @param solenoid solenoid to reverse
     */
    public ReverseDualActionSolenoid(DualActionSolenoidModule solenoid) {
        this.solenoid = solenoid;
    }

    /**
     * Reverses the direction of the solenoid. This means LEFT -> RIGHT or RIGHT
     * -> LEFT.
     */
    @Override
    public void run() {
        solenoid.reverse();
    }
}
