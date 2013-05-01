package edu.first.command;

import edu.first.bindings.Bind;

/**
 * Framework for all things that perform a specific action. Used prominently in
 * autonomous and bindings. Has similar but more open expectations as
 * {@link Runnable} in that a command can do anything, even something
 * non-finished. (does not complete an action)
 *
 * @author Joel Gallant
 */
public interface Command extends Runnable, Bind {

    /**
     * Without any restrictions or expectations, does what the command is
     * intended to do. Should not take extraneous amounts of time.
     */
    void run();
}
