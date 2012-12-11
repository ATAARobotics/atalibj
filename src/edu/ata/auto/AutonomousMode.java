package edu.ata.auto;

import edu.ata.commands.Command;
import edu.ata.commands.CommandGroup;

/**
 * Basic autonomous mode that takes {@link Command commands} and runs them
 * inside of {@link AutonomousMode#run()}. Is able to call these commands
 * simultaneously (concurrent) or sequentially (sequential).
 *
 * <p> To use {@link AutonomousMode}, create a new class that adds all of the
 * commands inside of the constructor. Creating a member {@link AutonomousMode}
 * will do nothing by default. For that reason, basically all uses of
 * {@code new AutonomousMode(Name)} is wrong.
 *
 * <p> To add commands to the Autonomous mode, use the commands in
 * {@link CommandGroup}. ({@link CommandGroup#addSequential(edu.ata.commands.Command) addSequential()},
 * {@link CommandGroup#addConcurrent(edu.ata.commands.Command) addConcurrent()}).
 *
 * <p> AutonomousMode is identical to {@link CommandGroup}. There is no
 * difference between them. AutonomousMode is here to identify CommandGroups
 * that are full autonomous modes. It serves as a type-safe way to tell the
 * program that it is a full autonomous mode.
 *
 * @see CommandGroup
 * @author Joel Gallant
 */
public class AutonomousMode extends CommandGroup {

    /**
     * Creates the autonomous mode with its name. Should add commands in
     * constructor.
     *
     * <p> The name of an autonomous mode should be short, concise and to the
     * point. It should outline its behavior but still say only what is needed.
     * The only purpose of names are to identify what is running to the user. It
     * is not used to compare objects. The user should be able to tell what the
     * autonomous mode does based on it's name only.
     *
     * <p> An AutonomousMode's name is non-editable.
     *
     * @param name name of the mode (shown to user)
     */
    public AutonomousMode(String name) {
        super(name);
    }
}
