package edu.ata.commands;

/**
 * Command used for running. Is semantically the exact same as {@link Runnable},
 * but is used to make the intention of sub-classes clearer.
 *
 * <p> No real general structure applies to all commands, and it is to the
 * discretion of the programmer to apply it in a meaningful way.
 *
 * <p> To use commands successfully (not using {@code command.run()}), use the
 * {@link Scheduler} class to properly call commands in the robot's context.
 *
 * @see Runnable
 * @see Scheduler
 * @author Joel Gallant
 */
public interface Command extends Runnable {
}
