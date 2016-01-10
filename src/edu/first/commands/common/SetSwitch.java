package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.identifiers.Position;
import edu.first.identifiers.StaticPosition;
import edu.first.identifiers.Switch;

/**
 * Sets the position of a switch.
 *
 * @see Switch#setPosition(boolean)
 * @since June 13 13
 * @author Joel Gallant
 */
public final class SetSwitch implements Command {

    private final Switch s;
    private final Position position;

    /**
     * Constructs the command using the switch and a position to get the value
     * from.
     *
     * @param s switch to set
     * @param position position to set switch to
     */
    public SetSwitch(Switch s, Position position) {
        this.s = s;
        this.position = position;
    }

    /**
     * Constructs the command using the switch and the position to switch to.
     *
     * @param s switch to set
     * @param position position to set switch to
     */
    public SetSwitch(Switch s, boolean position) {
        this(s, new StaticPosition(position));
    }

    /**
     * Changes the position of the switch to the input given.
     */
    @Override
    public void run() {
        s.setPosition(position.getPosition());
    }
}
