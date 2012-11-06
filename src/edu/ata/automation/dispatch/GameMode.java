package edu.ata.automation.dispatch;

/**
 * Basic class representing game modes. Handles running by itself.
 *
 * <p> Implemented by running in a new separate thread. Instantiates a new
 * thread each time it is started. This is done because threads are implicitly
 * only runnable once. This creates the need to re-create the {@link Thread}
 * object each time. Another bonus of this is that priority of the thread is
 * adjustable between runs if needed. ({@link GameMode#setPriority(int)}).
 *
 * @see Thread
 * @author Joel Gallant
 */
public abstract class GameMode implements Runnable {

    private final String name;
    private Thread thread;
    private int priority;

    /**
     * Creates the game mode with a name. Priority is set to
     * {@link Thread#NORM_PRIORITY}.
     *
     * @param name name of the thread
     */
    public GameMode(String name) {
        this(name, Thread.NORM_PRIORITY);
    }

    /**
     * Creates the game mode with a name and priority.
     *
     * @param name name of the thread
     * @param priority priority of the thread (1 - 10)
     * @see Thread#setPriority()
     */
    public GameMode(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * Gets the name of the thread.
     *
     * @return name of game mode
     */
    public final String getName() {
        return name;
    }

    /**
     * Creates a string representation of the thread. Is equal to:
     * <pre> "GameMode:"+obj.getName() </pre>
     *
     * @return string representation
     */
    public final String toString() {
        return "GameMode:" + name;
    }

    /**
     * Checks to see if the thread is still alive. If it is not running / is
     * null / is not registered as 'alive', returns false.
     *
     * @return whether the thread is alive
     * @see Thread#isAlive()
     */
    public final boolean isAlive() {
        if (thread == null) {
            return false;
        }
        return thread.isAlive();
    }

    /**
     * Starts the thread (Game Mode).
     *
     * <p> <i>Caution :</i> Thread will not be started if
     * {@link GameMode#isAlive()} does not return false. This prevents the game
     * mode from running more than once at the same time, but is prone to
     * concurrency problems. (If your program assumes that {@code start()}
     * actually starts the mode.)
     *
     * @see Thread
     */
    public final void start() {
        if (isAlive()) {
            return;
        }
        thread = new Thread(this, name);
        thread.setPriority(priority);
        thread.start();
    }

    /**
     * Sets the priority of the thread. Will take effect the next time you run
     * the game mode. ({@link GameMode#start()})
     *
     * @param priority priority of the thread (1-10)
     * @see Thread#setPriority(int)
     */
    public final void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Interrupts the game mode. Is overridden, and thus cannot be entirely
     * trusted.
     *
     * <p> Should usually wait until it is verified to be done. It would be
     * dangerous to just let things go on after calling {@code interrupt()}.
     */
    public abstract void interrupt();
}