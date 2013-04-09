package edu.first.module.subsystem;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.networktables2.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Subsystem implements Runnable, Module.DisableableModule {

    private static final List timers = new List();
    private Timer timer = new Timer();
    private final Subsystem instance = this;
    private final TimerTask task = new Task();
    private final Module[] modules;
    private boolean started = false;

    {
        timers.add(timer);
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
     * @param type the type of subsystem it is
     */
    public Subsystem(Module[] modules) {
        this.modules = modules;
    }

    public static void cancelAllSubsystems() {
        for (int x = 0; x < timers.size(); x++) {
            if (timers.get(x) instanceof Timer) {
                ((Timer) timers.get(x)).cancel();
            }
        }
    }

    public boolean isStarted() {
        return started;
    }

    public abstract void start();

    public final void stop() {
        timer.cancel();
        started = false;
        timer = new Timer();
    }

    protected void startOnce() {
        startOnce(0);
    }

    protected void startOnce(long delay) {
        if (!started) {
            timer.schedule(task, delay);
            started = true;
        }
    }

    protected void startAtFixedDelay(long fixedDelay) {
        startAtFixedDelay(0, fixedDelay);
    }

    protected void startAtFixedDelay(long delay, long fixedDelay) {
        if (!started) {
            timer.schedule(task, delay, fixedDelay);
            started = true;
        }
    }

    protected void startAtFixedRate(long fixedRate) {
        startAtFixedRate(0, fixedRate);
    }

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
        for (int x = 0; x < modules.length; x++) {
            if (modules[x] instanceof DisableableModule) {
                ((DisableableModule) modules[x]).disable();
            }
        }
        return !isEnabled() && disableSubsystem();
    }

    // Override to use
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
        return isEnabled() && enableSubsystem();
    }

    // Override to use
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

    // Override to use
    protected boolean subsystemEnabled() {
        return true;
    }

    private final class Task extends TimerTask {

        public void run() {
            instance.run();
        }
    }
}
