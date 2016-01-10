package edu.first.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.first.command.Command;

/**
 * Command that encompasses multiple commands strung together. Runs commands
 * sequentially or concurrently in groups, in the order of how they are added.
 * Is only designed to be subclassed, and should not be instantiated as itself
 * (thus the only constructor being protected).
 *
 * <p>
 * Sequential commands are run after the command before it, and are done before
 * the next command.
 *
 * <p>
 * Concurrent commands are run at the same time as all concurrent commands
 * around it. A sequential command between concurrent commands will prevent
 * those concurrent commands from being run together.
 *
 * <p>
 * To add sequential command, use
 * {@link #appendSequential(edu.first.command.Command)}, and to add concurrent
 * commands, use {@link #appendConcurrent(edu.first.command.Command)}.
 *
 * <p>
 * Command Groups look like this:
 * <pre>
 * public final class ExampleCommand extends CommandGroup {
 *     public ExampleCommand() {
 *         appendSequential(new F());
 *         appendSequential(new F1());
 *         appendConcurrent(new F2());
 *         appendConcurrent(new F3());
 *         appendConcurrent(new F4());
 *         appendSequential(new F5());
 *         appendConcurrent(new F6());
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

    private final List<Command> commands = new ArrayList<>();

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
     * @deprecated use {@link #appendSequential(edu.first.command.Command)}
     * instead
     */
    public final void addSequential(Command command) {
        appendSequential(command);
    }

    /**
     * Adds a command to part of the queue in this command group.
     *
     * @throws NullPointerException when command is null
     * @param command command to run by itself
     */
    public final void appendSequential(Command command) {
        if (command == null) {
            throw new NullPointerException("Null command given");
        }
        commands.add(command);
    }

    /**
     * Adds a command to part of the queue in this command group, that will be
     * run at the same time as all other concurrent commands around it.
     *
     * @throws NullPointerException when command is null
     * @param command command to run alongside other concurrent commands
     * @deprecated use {@link #appendConcurrent(edu.first.command.Command)}
     * instead
     */
    public final void addConcurrent(Command command) {
        appendConcurrent(command);
    }

    /**
     * Adds a command to part of the queue in this command group, that will be
     * run at the same time as all other concurrent commands around it.
     * Concurrent commands are run at the same time as all concurrent commands
     * around it.
     *
     * <p>
     * <i> The connotation of "commands around it" is commands the are added
     * before and after this command, that are added using
     * {@link #appendConcurrent(edu.ATA.command.Command)}.</i>
     *
     * @throws NullPointerException when command is null
     * @param command command to run alongside other concurrent commands
     */
    public final void appendConcurrent(Command command) {
        if (command == null) {
            throw new NullPointerException("Null command given");
        }
        Object lastCommand = commands.get(commands.size() - 1);
        if (!(lastCommand instanceof ConcurrentCommandGroup)) {
            commands.add(new ConcurrentCommandGroup());
        }
        // list is sure to end with a ConcurrentCommandGroup
        ((ConcurrentCommandGroup) commands.get(commands.size() - 1)).add(command);
    }

    /**
     * Runs all commands that have been added to this command group in the order
     * they have been added. To understand how they are run, see the
     * documentation of
     * {@link #appendSequential(edu.ATA.command.Command) appendSequential(Command)}
     * and
     * {@link #appendConcurrent(edu.ATA.command.Command) appendConcurrent(Command)}.
     */
    @Override
    public final void run() {
        Iterator<Command> i = commands.iterator();
        while (i.hasNext()) {
            Command c = (Command) i.next();
            c.run();
        }
    }
}
