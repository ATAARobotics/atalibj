package edu.first.commands;

import edu.first.command.Command;
import edu.first.utils.Logger;
import java.utils.ArrayList;

/**
 * Basic command group that runs multiple commands in parallel. Each command is
 * run inside of a different thread.
 *
 * @author Joel Gallant
 */
public class ConcurrentCommandGroup implements Command {

    private final Command[] commands;

    /**
     * Constructs the command group using an array of commands to be run at the
     * same time. There is no limit on how many commands can be run. The array
     * cannot be null.
     *
     * @param commands commands to run concurrently
     */
    public ConcurrentCommandGroup(Command[] commands) {
        if (commands == null) {
            throw new NullPointerException();
        }
        this.commands = commands;
    }

    /**
     * Constructs the command group using an array of commands to be run at the
     * same time. There is no limit on how many commands can be run. The list
     * cannot be null, and cannot contain elements that are not commands.
     *
     * @param commands commands to run concurrently
     */
    public ConcurrentCommandGroup(ArrayList commands) {
        if (commands == null) {
            throw new NullPointerException();
        }
        this.commands = new Command[commands.size()];
        for (int x = 0; x < commands.size(); x++) {
            this.commands[x] = (Command) commands.get(x);
        }
    }

    /**
     * Runs all of the commands, and waits for them all to finish.
     */
    public void run() {
        try {
            Thread[] threads = new Thread[commands.length];
            for (int x = 0; x < commands.length; x++) {
                threads[x] = new Thread(commands[x]);
            }
            for (int x = 0; x < threads.length; x++) {
                threads[x].start();
            }
            for (int x = 0; x < threads.length; x++) {
                threads[x].join();
            }
        } catch (InterruptedException ex) {
            Logger.log(Logger.Urgency.USERMESSAGE, "CommandGroup interrupted");
            ex.printStackTrace();
        }
    }
}
