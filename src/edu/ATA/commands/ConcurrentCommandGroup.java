package edu.ATA.commands;

/**
 * Basic command group that runs multiple commands in parallel. Each command is
 * run inside of a different thread.
 *
 * @author joel
 */
public class ConcurrentCommandGroup implements Command {
    
    private Command[] commands;

    /**
     * Constructs the command group using an array of commands to be run at the
     * same time. There is no limit on how many commands can be run. The array
     * cannot be null.
     *
     * @param commands commands to run concurrently
     */
    public ConcurrentCommandGroup(Command[] commands) {
        if(commands == null) {
            throw new NullPointerException();
        }
        this.commands = commands;
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
        }
    }
}
