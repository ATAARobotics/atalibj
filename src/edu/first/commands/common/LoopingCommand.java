package edu.first.commands.common;

import edu.first.command.Command;

/**
 * Command that runs in a loop until a condition returns false.
 *
 * @since June 17 13
 * @author Joel Gallant
 */
public abstract class LoopingCommand implements Command {

    /**
     * Runs {@link #runLoop()} until {@link #continueLoop()} returns false.
     */
    @Override
    public final void run() {
        while (continueLoop()) {
            runLoop();
        }
    }

    /**
     * Returns whether the loop should run again.
     *
     * @return if loop should continue
     */
    public abstract boolean continueLoop();

    /**
     * Runs the actual instructions of the command.
     */
    public abstract void runLoop();
}
