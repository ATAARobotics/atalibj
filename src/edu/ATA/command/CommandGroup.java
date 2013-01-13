package edu.ATA.command;

import edu.wpi.first.wpilibj.networktables2.util.List;

/**
 * Command that encompasses multiple commands strung together. Runs commands
 * sequentially or concurrently in groups, in the order of how they are added.
 * Is only designed to be subclasses, and should not be instantiated as itself
 * (thus the only constructor being protected).
 *
 * <p> Sequential commands are run after the command before it, and are done
 * before the next command.
 *
 * <p> Concurrent commands are run at the same time as all concurrent commands
 * around it.
 *
 * <p> To add sequential command, use
 * {@link CommandGroup#addSequential(edu.ATA.command.Command)}, and to add
 * concurrent commands, use
 * {@link CommandGroup#addConcurrent(edu.ATA.command.Command)}.
 *
 * @author Joel Gallant
 */
public class CommandGroup implements Command {

    private static final Integer SEQUENTIAL = Integer.valueOf(0),
            CONCURRENT = Integer.valueOf(1);
    private final List types = new List();
    private final List commands = new List();

    /**
     * Protected constructor to prevent instantiating from other classes.
     */
    protected CommandGroup() {
    }

    /**
     * Adds a command to part of the queue in this command group. Sequential
     * commands are run after the command before it, and are done before the
     * next command.
     *
     * @param command command to run by itself
     */
    protected final void addSequential(Command command) {
        add(SEQUENTIAL, command);
    }

    /**
     * Adds a command to part of the queue in this command group, that will be
     * run at the same time as all other concurrent commands around it.
     * Concurrent commands are run at the same time as all concurrent commands
     * around it.
     *
     * <p> <i> The connotation of "commands around it" is commands the are added
     * before and after this command, that are concurrently added.</i>
     *
     * @param command command to run alongside other concurrent commands
     */
    protected final void addConcurrent(Command command) {
        add(CONCURRENT, command);
    }

    private void add(Integer type, Command command) {
        if (type == null || command == null) {
            throw new NullPointerException();
        }
        types.add(type);
        commands.add(command);
    }

    /**
     * Runs all commands that have been added to this command group in the order
     * they have been added. To understand how they are run, see the
     * documentation of
     * {@link CommandGroup#addSequential(edu.ATA.command.Command) addSequential(Command)}
     * and
     * {@link CommandGroup#addConcurrent(edu.ATA.command.Command) addConcurrent(Command)}.
     */
    public final void runCommand() {
        List concurrent = new List();
        for (int x = 0; x < commands.size(); x++) {
            if (types.get(x) == CONCURRENT) {
                concurrent.add(commands.get(x));
            } else {
                if (!concurrent.isEmpty()) {
                    new ConcurrentCommandGroup(concurrent).runCommand();
                    concurrent = new List();
                }
                ((Command) commands.get(x)).runCommand();
            }
        }
        if (!concurrent.isEmpty()) {
            new ConcurrentCommandGroup(concurrent).runCommand();
        }
    }

    private final class ConcurrentCommandGroup implements Command {

        private final Command[] commands;

        private ConcurrentCommandGroup(Command[] commands) {
            this.commands = commands;
        }

        private ConcurrentCommandGroup(List list) {
            commands = new Command[list.size()];
            for (int x = 0; x < list.size(); x++) {
                commands[x] = (Command) list.get(x);
            }
        }

        public void runCommand() {
            Commands.runConcurrently(commands);
        }
    }
}
