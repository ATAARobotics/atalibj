package edu.ata.auto;

import edu.ata.commands.Command;
import edu.ata.commands.CommandGroup;

/**
 * Basic autonomous mode that takes {@link Command commands} and runs them
 * inside of {@link AutonomousMode#run()}. Is able to call these commands
 * simultaneously (concurrent) or sequentially (sequential).
 *
 * To use {@link AutonomousMode}, create a new class that adds all of the
 * commands inside of the constructor. Creating a member {@link AutonomousMode}
 * will do nothing by default. For that reason, basically all uses of
 * {@code new AutonomousMode(Name)} is wrong.
 *
 * To add commands to the Autonomous mode, use the commands in
 * {@link CommandGroup}. ({@code addSequential()}, {@code addConcurrent()}.
 *
 * @see CommandGroup
 * @author Joel Gallant
 */
public class AutonomousMode extends CommandGroup {

    private final String name;

    /**
     * Creates the autonomous mode with its name. Should add commands in
     * constructor.
     *
     * The name of an autonomous mode should be short, concise and to the point.
     * It should outline its behavior but still say only what is needed. The
     * only purpose of names are to identify what is running to the user. It is
     * not used to compare objects.
     *
     * @param name name of the mode (shown to user)
     */
    public AutonomousMode(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the autonomous mode. The name can never change, so in
     * practice this method could be used to identify whether or not the mode is
     * the same.
     *
     * @return name of the autonomous mode
     */
    public String getName() {
        return name;
    }
}
