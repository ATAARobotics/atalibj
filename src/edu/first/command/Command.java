package edu.first.command;

/**
 * Framework for all things that perform a specific action. Has similar but more
 * open expectations as {@link Runnable} in that a command can do anything, even
 * something non-finished. (does not complete an action)
 *
 * Command is meant to replicate the functionality introduced in the
 * Command-Based model of WPILibJ. The concept is great, but implementation left
 * something to be desired.
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
    @Override
    public void run();
}
