package edu.ATA.bindings;

import edu.ATA.commands.ConcurrentCommandGroup;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class MultipleCommandBind extends ConcurrentCommandGroup implements CommandBind {

    public MultipleCommandBind(CommandBind[] commands) {
        super(commands);
    }
}
