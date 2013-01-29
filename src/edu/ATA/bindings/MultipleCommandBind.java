package edu.ATA.bindings;

import edu.ATA.commands.ConcurrentCommandGroup;

/**
 * Command bind that runs multiple commands concurrently. Is basically
 * equivalent to {@link ConcurrentCommandGroup}, just implementing
 * {@link CommandBind}.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class MultipleCommandBind extends ConcurrentCommandGroup implements CommandBind {

    /**
     * Constructs the command group using an array of commands to be run at the
     * same time. There is no limit on how many commands can be run. The array
     * cannot be null.
     *
     * @param commands commands to run concurrently
     */
    public MultipleCommandBind(CommandBind[] commands) {
        super(commands);
    }
}
