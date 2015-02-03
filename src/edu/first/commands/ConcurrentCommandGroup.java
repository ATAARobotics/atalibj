package edu.first.commands;

import java.util.ArrayList;
import java.util.List;
import edu.first.command.Command;
import edu.first.util.log.Logger;

/**
 * Basic command group that runs multiple commands in parallel. Every command
 * inside of this group is started at the same time.
 *
 * @since May 26 13
 * @author Joel Gallant
 */
public final class ConcurrentCommandGroup implements Command {

    private final List<Command> commands;

    /**
     * Constructs the command group using an array of commands to be run at the
     * same time. There is no limit on how many commands can be run.
     */
    public ConcurrentCommandGroup() {
        this.commands = new ArrayList<>();
    }

    /**
     * Constructs the command group using an array of commands to be run at the
     * same time. There is no limit on how many commands can be run. The
     * ArrayList cannot be null, and cannot contain elements that are not
     * commands.
     *
     * @throws NullPointerException when array is null
     * @param commands commands to run concurrently
     */
    public ConcurrentCommandGroup(List<Command> commands) {
        if (commands == null) {
            throw new NullPointerException("Array is null");
        }
        this.commands = commands;
    }

    /**
     * Adds a command to the command group. The command cannot be null.
     *
     * @throws NullPointerException when the command is null
     * @param command the command to add to the group
     */
    public void add(Command command) {
        if (command == null) {
            throw new NullPointerException("Null command given");
        }
        commands.add(command);
    }

    /**
     * Runs all of the commands, and waits for them all to finish.
     *
     * @throws RuntimeException when threads are somehow interrupted
     */
    @Override
    public void run() {
        try {
            Thread[] threads = new Thread[commands.size()];
            for (int x = 0; x < commands.size(); x++) {
                threads[x] = new Thread((Command) commands.get(x));
            }
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass()).error("Command Group interrupted", ex);
            throw new RuntimeException("Command Group interrupted - " + ex.getMessage());
        }
    }
}
