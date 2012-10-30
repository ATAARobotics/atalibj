package edu.ata.automation.dispatch;

/**
 * Basic interface for autonomous modes. Initializes, does things, then closes.
 *
 * @author Joel Gallant
 */
public interface Autonomous {

    /**
     * Initializes the mode.
     */
    public abstract void init();

    /**
     * Runs the mode. Should take care of stopping itself if needed.
     */
    public abstract void main();

    /**
     * Closes the mode.
     */
    public abstract void close();
}
