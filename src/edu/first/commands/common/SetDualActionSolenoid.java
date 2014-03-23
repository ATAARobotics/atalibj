package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.actuators.DualActionSolenoid;
import edu.first.module.actuators.DualActionSolenoidModule;

/**
 * Sets the state of a dual action solenoid.
 *
 * @see
 * DualActionSolenoid#set(edu.first.module.actuators.DualActionSolenoid.Direction)
 * @since June 13 13
 * @author Joel Gallant
 */
public final class SetDualActionSolenoid implements Command {

    private final DualActionSolenoid solenoid;
    private final DualActionSolenoid.Direction direction;

    /**
     * Constructs the command using the solenoid to use and which direction to
     * move it.
     *
     * @param solenoid solenoid to change the state of
     * @param direction direction to set the solenoid
     */
    public SetDualActionSolenoid(DualActionSolenoid solenoid, DualActionSolenoidModule.Direction direction) {
        this.solenoid = solenoid;
        this.direction = direction;
    }

    /**
     * Sets the solenoid to the given direction.
     */
    public void run() {
        solenoid.set(direction);
    }
}
