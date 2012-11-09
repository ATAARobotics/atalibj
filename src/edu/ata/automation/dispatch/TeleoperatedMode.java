package edu.ata.automation.dispatch;

/**
 * Basic class used for representing different teleoperated modes. Has a basic
 * looping structure that can be interrupted easily using the
 * {@code interrupted} field. Is thread safe interrupting, so the mode can be
 * stopped from anywhere in the code. (Even though it's definitely not
 * recommended)
 *
 * <p> Stops the loop from deadlocking by sleeping the thread each loop for 1-5
 * milliseconds.
 *
 * @see GameMode
 * @author Joel Gallant
 */
public abstract class TeleoperatedMode extends GameMode {

    private boolean interrupted = false;

    /**
     * Creates the teleop mode with a name. Using the thread priority of 9.
     * (Very high)
     *
     * @see GameMode
     * @see Thread#getPriority()
     * @param name name of the mode
     */
    public TeleoperatedMode(String name) {
        super(name, 9);
    }

    /**
     * Main method for the teleoperated game mode. Goes in a loop continuously,
     * with a delay of 1-5 milliseconds. Is <b>not</b> guaranteed to be periodic
     * in any sense other than on the processor, which is completely dependant
     * on the network connection.
     */
    public abstract void loop();

    /*

     Is not final so that if you wanted to have something happen at the end,
     you could overide the method using:

     public void interrupt() {
     super.interrupt();
     ... Business code ...
     }

     */
    public void interrupt() {
        synchronized (this) {
            interrupted = true;
        }
        try {
            join();
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Runs the mode. Is used in {@link GameMode}, and should not be used
     * anywhere else.
     */
    public final void run() {
        boolean c;
        synchronized (this) {
            interrupted = false;
            c = interrupted;
        }
        while (!c) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                // Impossible - a 1 millisecond sleep is only there to prevent deadlock
            }
            loop();
            synchronized (this) {
                c = interrupted;
            }
        }
    }
}
