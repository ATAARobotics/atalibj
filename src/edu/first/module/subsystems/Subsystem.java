package edu.first.module.subsystems;

import edu.first.module.Module;

/**
 * Groups of modules that perform more useful functions than actuators and
 * sensors. A subsystem should perform some kind of action(s) that would be
 * impossible for the individual modules themselves to do. Subsystems are things
 * like "arms", "shooters" and "compressors". They are elements of the robot
 * with multiple components which act together.
 *
 * <p>
 * All components in a subsystem should be passed into the constructor
 * ({@link Subsystem#Subsystem(edu.first.module.Module[])}). There should be no
 * modules that are not put into that array.
 *
 * <p>
 * All modules that are passed in the constructor are called in the order of the
 * array in {@link #init()}, {@link #enable()}, {@link #disable()} and
 * {@link #isEnabled()}. The subsystem does all of the work to keep its members
 * in the same state that it is.
 *
 * <p>
 * If the programmer wants the subsystem to perform any special function
 * specific to the subsystem in
 * {@link #init()}, {@link #enable()}, {@link #disable()} and/or
 * {@link #isEnabled()}, they can use
 * {@link #initSubsystem()}, {@link #enableSubsystem()}, {@link #disableSubsystem()}
 * and/or {@link #isSubsystemEnabled()} respectively to do those functions. Just
 * override them and they will be called in the {@link Module} methods.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public class Subsystem implements Module {

    private final Module[] modules;

    /**
     * Constructs the subsystem by passing an array with all of the modules used
     * in the subsystem. These modules are initialized, enabled and disabled
     * along with this subsystem.
     *
     * @throws NullPointerException when array is null
     * @param modules array of all the modules to be initialized, enabled and
     * disabled with this subsystem
     */
    public Subsystem(Module[] modules) {
        if (modules == null) {
            throw new NullPointerException("Null array given");
        }
        this.modules = modules;
    }

    /**
     * Placeholder method that is called after the internal modules are
     * initialized. This should ensure the module is "ready" to work.
     *
     * <p>
     * <i> Override this method to use it. No super call is necessary. </i>
     */
    protected void initSubsystem() {
    }

    /**
     * Placeholder method that is called after the internal modules are enabled.
     * This should ensure the module's functions are "ready" to work.
     *
     * <p>
     * <i> Override this method to use it. No super call is necessary. </i>
     */
    protected void enableSubsystem() {
    }

    /**
     * Placeholder method that is called before the internal modules are
     * disabled. This should ensure the module's functions are no longer "ready"
     * to work.
     *
     * <p>
     * <i> Override this method to use it. No super call is necessary. </i>
     */
    protected void disableSubsystem() {
    }

    /**
     * Placeholder method that is called along with {@link #isEnabled()}. This
     * should return whether the module's functions are "ready" to work.
     *
     * <p>
     * <i> Override this method to use it. No super call is necessary. </i>
     *
     * @return if subsystem is enabled
     */
    protected boolean isSubsystemEnabled() {
        return true;
    }

    /**
     * Calls {@link Module#init()} on every module in the subsystem.
     *
     * <p>
     * Calls {@link #initSubsystem()} after it initializes the modules.
     */
    @Override
    public final void init() {
        for (Module module : modules) {
            module.init();
        }
        initSubsystem();
    }

    /**
     * Calls {@link Module#enable()} on every module in the subsystem.
     *
     * <p>
     * Calls {@link #enableSubsystem()} after it enables the modules.
     */
    @Override
    public final void enable() {
        for (Module module : modules) {
            module.enable();
        }
        enableSubsystem();
    }

    /**
     * Calls {@link Module#disable()} on every module in the subsystem.
     *
     * <p>
     * Calls {@link #disableSubsystem()} before it disables the modules.
     */
    @Override
    public final void disable() {
        disableSubsystem();
        for (Module module : modules) {
            module.disable();
        }
    }

    /**
     * Returns whether every module is {@link Module#isEnabled() enabled}, and
     * that the subsystem is {@link #isSubsystemEnabled() enabled}.
     *
     * @return if everything is enabled
     */
    @Override
    public final boolean isEnabled() {
        for (Module module : modules) {
            if (!module.isEnabled()) {
                return false;
            }
        }
        return isSubsystemEnabled();
    }
}
