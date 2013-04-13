package edu.first.module.subsystem;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.networktables2.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A subsystem that contains modules and potentially a thread. Is basically
 * capable of doing anything. All subsystems should be final, since they are the
 * highest level you should work on.
 *
 * @author Joel Gallant
 */
public abstract class Subsystem implements Runnable, Module.DisableableModule {

    private static final List subsystems = new List();
    private Timer timer = new Timer();
    private final Subsystem instance = this;
    private final TimerTask task = new Task();
    private final Module[] modules;
    private boolean started = false;

    {
        subsystems.add(this);
    }

    /**
     * Constructs the module using an array of modules to enable and disable
     * along with this one.
     *
     * <p> You should include this paragraph to explain how it works:
     *
     * <p> Requires them to be modules so that enabling and disabling can be
     * guaranteed. The module aspect of the given objects is handled by the
     * inner methods of this class. (enabling, disabling) It's generally
     * advisable to only use the modules here, and not use them elsewhere, as
     * references to the same objects can cause conflicts.
     *
     * <p> There are implicit problems with extending a subsystem. See the
     * documentation for this class for more details.
     *
     * @param modules modules to enable and disable in this subsystem
     */
    public Subsystem(Module[] modules) {
        this.modules = modules;
    }

    /**
     * Stops all subsystems. Calls {@link Subsystem#stop()} on all subsystems.
     */
    public static void stopAllSubsystems() {
        for (int x = 0; x < subsystems.size(); x++) {
            if (subsystems.get(x) instanceof Timer) {
                ((Subsystem) subsystems.get(x)).stop();
            }
        }
    }

    /**
     * Returns whether or not the subsystem has been started.
     *
     * @return if subsystem was started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Starts the subsystem. Is abstract to allow subsystems to run at different
     * rates and ways.
     */
    public abstract void start();

    /**
     * Stops the running of the subsystem. Cannot stop run(), but will not run
     * anymore.
     */
    public final void stop() {
        timer.cancel();
        started = false;
        timer = new Timer();
    }

    /**
     * Starts the subsystem to run once.
     */
    protected void startOnce() {
        startOnce(0);
    }

    /**
     * Starts the subsystem to run once.
     *
     * @param delay time before running
     */
    protected void startOnce(long delay) {
        if (!started) {
            timer.schedule(task, delay);
            started = true;
        }
    }

    /**
     * Starts the subsystem if it's not started already at a fixed delay between
     * runs.
     *
     * @param fixedDelay delay between runs
     * @see Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)
     */
    protected void startAtFixedDelay(long fixedDelay) {
        startAtFixedDelay(0, fixedDelay);
    }

    /**
     * Starts the subsystem if it's not started already at a fixed delay between
     * runs.
     *
     * @param delay time before starting
     * @param fixedDelay delay between runs
     * @see Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)
     */
    protected void startAtFixedDelay(long delay, long fixedDelay) {
        if (!started) {
            timer.schedule(task, delay, fixedDelay);
            started = true;
        }
    }

    /**
     * Starts the subsystem if it's not started already at a fixed rate.
     *
     * @param fixedRate rate to run at
     * @see Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)
     */
    protected void startAtFixedRate(long fixedRate) {
        startAtFixedRate(0, fixedRate);
    }

    /**
     * Starts the subsystem if it's not started already at a fixed rate.
     *
     * @param delay time before starting
     * @param fixedRate rate to run at
     * @see Timer#scheduleAtFixedRate(java.util.TimerTask, long, long)
     */
    protected void startAtFixedRate(long delay, long fixedRate) {
        if (!started) {
            timer.scheduleAtFixedRate(task, delay, fixedRate);
            started = true;
        }
    }

    /**
     * Disables all modules that were submitted in
     * {@link Subsystem#Subsystem(edu.ATA.module.Module[])}.
     *
     * @return if there are modules disabled
     */
    public final boolean disable() {
        boolean subDone = disableSubsystem();
        for (int x = 0; x < modules.length; x++) {
            if (modules[x] instanceof DisableableModule) {
                ((DisableableModule) modules[x]).disable();
            }
        }
        stop();
        return !isEnabled() && subDone;
    }

    /**
     * Override this method to use it. Disables the subsystem.
     *
     * @return if not overriden, returns true
     */
    protected boolean disableSubsystem() {
        return true;
    }

    /**
     * Enables all modules that were submitted in
     * {@link Subsystem#Subsystem(edu.ATA.module.Module[])}.
     *
     * @return if all modules are enabled
     */
    public final boolean enable() {
        for (int x = 0; x < modules.length; x++) {
            modules[x].enable();
        }
        boolean subOn = enableSubsystem();

        start();
        return isEnabled() && subOn;
    }

    /**
     * Override this method to use it. Enables the subsystem.
     *
     * @return if not overriden, returns true
     */
    protected boolean enableSubsystem() {
        return true;
    }

    /**
     * Checks if all modules are enabled.
     *
     * @return if all modules are enabled
     */
    public final boolean isEnabled() {
        for (int x = 0; x < modules.length; x++) {
            if (!modules[x].isEnabled()) {
                return false;
            }
        }
        return subsystemEnabled();
    }

    /**
     * Override this method to use it. Returns whether the subsystem is enabled.
     *
     * @return if not overriden, returns true
     */
    protected boolean subsystemEnabled() {
        return true;
    }

    private final class Task extends TimerTask {

        public void run() {
            instance.run();
        }
    }
}
