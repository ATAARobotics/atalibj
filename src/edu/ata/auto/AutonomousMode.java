package edu.ata.auto;

import edu.ata.commands.Command;
import edu.ata.commands.CommandGroup;

/**
 * Basic autonomous mode that takes {@link Command commands} and runs them
 * inside of {@link AutonomousMode#run()}. Is able to call these commands
 * simultaneously (concurrent) or sequentially (sequential).
 *
 * @author Joel Gallant
 */
public class AutonomousMode extends CommandGroup {

    private final String name;

    /**
     * Creates the autonomous mode with its name. Should add commands in
     * constructor.
     *
     * @param name name of the mode (displayed to user)
     */
    public AutonomousMode(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the autonomous mode. The name can never change.
     * 
     * @return name of the autonomous mode
     */
    public String getName() {
        return name;
    }
}
