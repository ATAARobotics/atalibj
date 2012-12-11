package edu.ata.commands;

/**
 * Command used for running. Is semantically the exact same as {@link Runnable},
 * but is used to make the intention of sub-classes clearer.
 *
 * <p> No real general structure applies to all commands, and it is to the
 * discretion of the programmer to apply it in a meaningful way. Be sure to
 * clarify in documentation.
 *
 * <p> To use commands successfully (not using {@code command.run()}), use the
 * {@link Scheduler} class to properly call commands in the robot's context.
 *
 * @see Runnable
 * @see Scheduler
 * @author Joel Gallant
 */
public abstract class Command implements Runnable {

    private final String name;

    /**
     * Creates the command without a name. Uses the class' name as its name.
     */
    public Command() {
        this.name = getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
    }

    /**
     * Creates the command with the given name.
     *
     * @param name name of the command
     */
    public Command(String name) {
        this.name = name;
    }

    /**
     * Returns the given name. When the name is explicitly set, it will be the
     * "Simple Name" (Class name) of the class.
     *
     * @return name of the command
     */
    public String getName() {
        return name;
    }
}
