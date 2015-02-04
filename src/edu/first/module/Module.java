package edu.first.module;

import edu.first.command.Command;
import edu.first.module.subsystems.Subsystem;

/**
 * A module is the standard for everything that performs some kind of function
 * on the robot. There are three general types of modules:
 *
 * <ul>
 * <li> Actuators and Sensors - the base parts of a robot
 * <li> Virtual control - virtual processes that actuate the first group (ex.
 * PID, bang-bang, TBH)
 * <li> {@link Subsystem Subsystems} - groups of modules that perform more
 * useful functions than the actuators and sensors
 * </ul>
 *
 * This interface creates some basic standards that are upheld across the entire
 * library. It lets classes do whatever they need to, while upholding a general
 * contract of how to behave. Specifically, it allows modules to be enabled and
 * disabled at will. When the user needs the functionality of a module, they can
 * enable it. When they do not need its function, they can disable it.
 *
 * The general contract for all modules is as follows:
 * <ul>
 * <li> Modules contain methods specific to their purpose, but do not expose
 * their composed instances for security reasons
 * <li> Modules are "ready" to work when {@link #init()} has been called, and
 * its functions will work after {@link #enable()} is called.
 * <li> "Settings" of a module, AKA its internal state, are changeable even when
 * disabled.
 * <li> Between calling {@link #enable()} and
 * {@link #disable()}, {@link #isEnabled()} will return true. Only
 * {@code enable()} and {@code disable()} can effect the output of
 * {@code isEnabled()}.
 * <li> If a function is called while the module is disabled, the function
 * should throw a {@link IllegalStateException}.
 * <li> Calling any of the methods more than once in a row will not behave
 * differently because of it.
 * <li> The state of {@link #isEnabled()} should be thread-safe and <i>only</i>
 * return {@code true} when it is completely enabled.
 * </ul>
 *
 * <p>
 * {@link Module.StartardModule} is a useful default implementation of
 * {@code Module} that fulfills most of the {@code Module} contract.
 *
 * @see StartardModule
 * @since May 22 13
 * @author Joel Gallant
 */
public interface Module {

    /**
     * Performs the necessary actions to ensure the module is "ready" to be
     * used. Whatever is not done in the constructor should be performed here.
     */
    public void init();

    /**
     * Performs the necessary actions to ensure the module is operational. The
     * module's functions should work after this method is called, provided this
     * method did not throw an error.
     *
     * <p>
     * The contract for this method is that {@link #isEnabled()} should return
     * {@code true} after this is called until {@link #disable()} is called. The
     * official {@code enabled} state of a module is only technically correct by
     * using {@link #isEnabled()} and the completion of this method does not by
     * definition mean that the module is enabled. By contract though,
     * completion of this method without error should mean that it is enabled.
     */
    public void enable();

    /**
     * Performs the necessary actions to ensure the module cannot be used. The
     * module's functions should not work after this method is called, provided
     * this method did not throw an error.
     *
     * <p>
     * The contract for this method is that {@link #isEnabled()} should return
     * {@code false} after this is called until {@link #enable()} is called. The
     * official {@code enabled} state of a module is only technically correct by
     * using {@link #isEnabled()} and the completion of this method does not by
     * definition mean that the module is disabled. By contract though,
     * completion of this method without error should mean that it is disabled.
     */
    public void disable();

    /**
     * Returns the {@code enabled} state of the module, indicating whether its
     * functions should work. This method should be thread-safe and only return
     * {@code true} if the module is <i>fully</i> enabled.
     *
     * <p>
     * When modules are not enabled, their functions (apart from "settings",
     * outlined in this {@link Module class} documentation) will throw an
     * {@link IllegalStateException}.
     *
     * @return if module is currently fully functional
     */
    public boolean isEnabled();

    /**
     * A default implementation of {@link Module} that provides a simpler
     * interface for building modules on.
     *
     * <p>
     * It fulfills theses elements of the {@link Module} contract:
     * <ul>
     * <li> Between calling {@link #enable()} and
     * {@link #disable()}, {@link #isEnabled()} will return true. Only
     * {@code enable()} and {@code disable()} can effect the output of
     * {@code isEnabled()}.
     * <li> If a function is called while the module is disabled, the function
     * should throw a {@link IllegalStateException}. (see
     * {@link #ensureEnabled()})
     * <li> The state of {@link #isEnabled()} should be thread-safe and
     * <i>only</i>
     * return {@code true} when it is completely enabled.
     * </ul>
     *
     * <p>
     * Parts that are not guaranteed by this class:
     * <ul>
     * <li> Modules contain methods specific to their purpose, but do not expose
     * their composed instances for security reasons
     * <li> Modules are "ready" to work when {@link #init()} has been called,
     * and its functions will work after {@link #enable()} is called.
     * <li> "Settings" of a module, AKA its internal state, are changeable even
     * when disabled.
     * <li> Calling any of the methods more than once in a row will not behave
     * differently because of it. (see {@link #enableModule()} and
     * {@link #disableModule()})
     * </ul>
     *
     * @since May 22 13
     * @author Joel Gallant
     */
    public static abstract class StandardModule implements Module {

        // uses lock and not "this" in case module uses "this/super" lock
        private final Object lock = new Object();
        private boolean enabled;

        /**
         * Command that enables this module.
         *
         * @see #enable()
         * @return command that enables the module
         */
        public final Command enableCommand() {
            return new Command() {
                @Override
                public void run() {
                    enable();
                }
            };
        }

        /**
         * Command that disables this module.
         *
         * @see #disable()
         * @return command that disables the module
         */
        public final Command disableCommand() {
            return new Command() {
                @Override
                public void run() {
                    disable();
                }
            };
        }

        /**
         * Performs the necessary actions to ensure the module is operational.
         * The module's functions should work after this method is called,
         * provided this method did not throw an error.
         */
        protected abstract void enableModule();

        /**
         * Performs the necessary actions to ensure the module cannot be used.
         * The module's functions should not work after this method is called,
         * provided this method did not throw an error.
         */
        protected abstract void disableModule();

        /**
         * {@inheritDoc}
         */
        @Override
        public final void enable() {
            if (!isEnabled()) {
                enableModule();
                synchronized (lock) {
                    enabled = true;
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final void disable() {
            if (isEnabled()) {
                disableModule();
                synchronized (lock) {
                    enabled = false;
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final boolean isEnabled() {
            synchronized (lock) {
                return enabled;
            }
        }

        /**
         * When this method is called and the module is not
         * {@link #isEnabled() enabled}, it will throw an
         * {@link IllegalStateException}.
         *
         * <p>
         * Use this method at the start of <b>all</b> functions that require the
         * module to be enabled.
         *
         * @see Module for more information on contract of throwing an
         * IllegalStateException
         * @throws IllegalStateException when module is not enabled
         */
        public final void ensureEnabled() {
            if (!isEnabled()) {
                throw new IllegalStateException(getClass().getName() + " was not enabled.");
            }
        }

        /**
         * Returns the name of the class, according to {@link #getClass()} and
         * {@link Class#getName()}.
         *
         * @return name of module's class
         */
        @Override
        public String toString() {
            return getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
        }
    }
}
