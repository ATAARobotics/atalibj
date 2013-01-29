package edu.ATA.bindings;

import edu.ATA.command.Command;

/**
 * Bind that is a command and a bind. This means it is used for bindable
 * joysticks, as well as command structures.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface CommandBind extends Command, Bind {
}
