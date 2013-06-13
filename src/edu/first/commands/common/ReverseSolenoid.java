package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.actuators.Solenoid;

/**
 * Reverses the direction of the solenoid. This means OFF -> ON or ON -> OFF.
 *
 * @see Solenoid#setPosition(boolean)
 * @since June 13 13
 * @author Joel Gallant
 */
public final class ReverseSolenoid implements Command {

    private final Solenoid solenoid;

    /**
     * Constructs the command using the solenoid to change.
     *
     * @param solenoid solenoid to reverse
     */
    public ReverseSolenoid(Solenoid solenoid) {
        this.solenoid = solenoid;
    }

    /**
     * Reverses the direction of the solenoid. This means OFF -> ON or ON ->
     * OFF.
     */
    public void run() {
        solenoid.setPosition(!solenoid.getPosition());
    }
}
