package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.actuators.Solenoid;

/**
 * Sets the position of a solenoid.
 *
 * @see Solenoid#setPosition(boolean)
 * @since June 13 13
 * @author Joel Gallant
 */
public final class SetSolenoid implements Command {

    private final Solenoid solenoid;
    private final boolean pos;

    /**
     * Constructs the command using the solenoid to use and the position to set
     * it to.
     *
     * @param solenoid solenoid to change position
     * @param pos position to switch solenoid to
     */
    public SetSolenoid(Solenoid solenoid, boolean pos) {
        this.solenoid = solenoid;
        this.pos = pos;
    }

    /**
     * Sets the position of the solenoid to the value given.
     */
    @Override
    public void run() {
        solenoid.setPosition(pos);
    }
}
