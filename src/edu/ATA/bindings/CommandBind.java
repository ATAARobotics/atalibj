package edu.ATA.bindings;

import edu.ATA.command.Command;

/**
 * Bind that performs a command when something happens. This means it is used
 * for bindable joysticks, usually running when a button is pressed or released.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface CommandBind extends Command, Bind {
}
