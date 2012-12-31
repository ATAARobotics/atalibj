package edu.ATA.command;

/**
 * Basic framework for all commands to be run, anywhere in the code. Similar to
 * {@link Runnable}, this interface has only one basic requirement: to be able
 * to 'run' something. The implementation of commands has absolutely no
 * requirements, but some general assumptions are made if a class implements
 * {@link Command}.
 *
 * <pre>
 * 1. The class does something, meaning that it physically manipulates
 * itself in some way, or performs some action useful to the drivers.
 *
 * 2. The class is intentioned to be manipulated by all utilies that
 * interact with {@link Command}. (ie. {@link Commands})
 * </pre>
 *
 * <p> Commands are most prevalent in autonomous mode, although their use
 * extends far from only autonomous mode, and is not limited in that way.
 *
 * @author Joel Gallant
 */
public interface Command {

    /**
     * With no assumptions being made, performs the intended function of the
     * class. This can involve many different possibilities, and it is typically
     * unsafe to assume any of them.
     */
    void runCommand();
}
