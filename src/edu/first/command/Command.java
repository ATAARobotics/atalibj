package edu.first.command;

/**
 * Framework for all things that perform a specific action. Has similar but more
 * open expectations as {@link Runnable} in that a command can do anything, even
 * something non-finished. (does not complete an action)
 *
 * @since May 26 13
 * @author Joel Gallant
 */
public interface Command extends Runnable {

    /**
     * Without any restrictions or expectations, does what the command is
     * intended to do. Should document if it will take extraneous amounts of
     * time.
     */
    public void run();
}
