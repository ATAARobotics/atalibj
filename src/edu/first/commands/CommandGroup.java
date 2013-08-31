package edu.first.commands;

import edu.first.command.Command;
import edu.first.util.list.ArrayList;
import edu.first.util.list.Iterator;
import edu.first.util.list.List;

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
 * around it. A sequential command between concurrent commands will be prevent
 * those concurrent commands from being run together.
 *
 * <p> To add sequential command, use
 * {@link CommandGroup#addSequential(edu.ATA.command.Command)}, and to add
 * concurrent commands, use
 * {@link CommandGroup#addConcurrent(edu.ATA.command.Command)}.
 *
 * <p> Command Groups look like this:
 * <pre>
 * public final class ExampleCommand extends CommandGroup {
 *     public ExampleCommand() {
 *         addSequential(new F());
 *         addSequential(new F1());
 *         addConcurrent(new F2());
 *         addConcurrent(new F3());
 *         addConcurrent(new F4());
 *         addSequential(new F5());
 *         addConcurrent(new F6());
 *     }
 * }
 * </pre>
 *
 * In this example, this execution will go as follows:
 * <pre>
 * F
 * F1
 * F2 + F3 + F4
 * F5
 * F6
 * </pre>
 *
 * @since May 26 13
 * @author Joel Gallant
 */
public class CommandGroup implements Command {

    private final List commands = new ArrayList();

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
     * @throws NullPointerException when command is null
     * @param command command to run by itself
     */
    public final void addSequential(Command command) {
        if (command == null) {
            throw new NullPointerException("Null command given");
        }
        commands.add(command);
    }

    /**
     * Adds a command to part of the queue in this command group, that will be
     * run at the same time as all other concurrent commands around it.
     * Concurrent commands are run at the same time as all concurrent commands
     * around it.
     *
     * <p> <i> The connotation of "commands around it" is commands the are added
     * before and after this command, that are added using
     * {@link CommandGroup#addConcurrent(edu.ATA.command.Command)}.</i>
     *
     * @throws NullPointerException when command is null
     * @param command command to run alongside other concurrent commands
     */
    public final void addConcurrent(Command command) {
        if (command == null) {
            throw new NullPointerException("Null command given");
        }
        if (!(commands.get(commands.size() - 1) instanceof ConcurrentCommandGroup)) {
            commands.add(new ConcurrentCommandGroup());
        }
        ((ConcurrentCommandGroup) commands.get(commands.size() - 1)).add(command);
    }

    /**
     * Runs all commands that have been added to this command group in the order
     * they have been added. To understand how they are run, see the
     * documentation of
     * {@link CommandGroup#addSequential(edu.ATA.command.Command) addSequential(Command)}
     * and
     * {@link CommandGroup#addConcurrent(edu.ATA.command.Command) addConcurrent(Command)}.
     */
    public final void run() {
        Iterator i = commands.iterator();
        while (i.hasNext()) {
            Command c = (Command) i.next();
            c.run();
        }
    }
}
