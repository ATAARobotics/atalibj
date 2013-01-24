package edu.ATA.command;

/**
 * Framework for all things that perform a specific action. Used prominently in
 * autonomous and bindings.
 *
 * @author Joel Gallant
 */
public interface Command extends Runnable {

    /**
     * Without any restrictions or expectations, does what the command is
     * intended to do. Should not take extraneous amounts of time.
     */
    void run();
}
