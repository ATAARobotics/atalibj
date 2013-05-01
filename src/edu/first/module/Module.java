package edu.first.module;

import edu.first.module.target.PIDModule;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.networktables2.util.List;

/**
 * Very basic framework for all "things". This interface makes things easy for
 * programmers to ensure that their code works modularly and functionally.
 * Understandably, it can be difficult to create a class without making it
 * dependant on other features in other classes. It can also be difficult to
 * ensure that for certain periods only some things should function (sensors,
 * motors, etc.). This interface is specifically designed to create a simple,
 * easy-to-understand way of creating classes that are easy to deal with, have a
 * standard way of being used, and are as expandable and extendable as possible.
 *
 * <p> Specifically, this interface creates a very basic standard for enabling
 * things. Instead of using 100 different methods for different things to make
 * them usable (see {@link Encoder}, {@link PIDController}, etc.), a simple
 * {@code enable()} method is enforced on modules. Obviously if a class has no
 * need to be enabled, this method would have no effect, but it is far more
 * useful to have a standard than risk time-consuming API documentation reading.
 *
 * <p> Most things that interact with the "real world" should be modules. This
 * means motors, drive trains, sensors, arms, shooters, and anything else that
 * can potentially move or interact. <i> This, however, does not exclude virtual
 * components from being modules.</i> For example, {@link PIDModule} is a
 * virtual component, but is a module for a good reason. Anything virtual thing
 * that could be regarded as an entity by itself is a good candidate for being a
 * module. State machines are another good example.
 *
 * <p> The module class also contains two implementations within itself that add
 * slightly more functionality. The first one is {@link DisableableModule},
 * which is exactly as its name suggests. It adds a disable method, making
 * another standard for things that can be "turned off". The second
 * implementation is {@link TrackedModule}. This makes the module something that
 * is added to {@link Module#MODULES} when it is constructed. Part of the
 * initial design of this interface (Module was originally a class that was
 * basically equivalent to {@link TrackedModule}), tracked modules make it easy
 * for instances to be tracked by checking mechanisms outside of the module to
 * understand why errors may be occurring. This feature has been changed from
 * mandatory to optional for two specific reasons: <b>1.</b> It is significantly
 * easier to program modules that extend their real underlying counterparts
 * <b>2.</b> Tracking module instances lends itself to unwanted safety hazards
 * from unknown code - it should be opt-in to lend your module object to
 * {@link Module#MODULES}. You don't want people messing with instances of your
 * module.
 *
 * @author Joel Gallant
 */
public interface Module {

    /**
     * A globally available list of all {@link TrackedModule} modules. This
     * object is not thread-safe, and uses an unsynchronized list. Extreme
     * precaution should be taken when using this object.
     */
    List MODULES = new List();

    /**
     * "Enables" the module. This has very loose standards, and could mean many
     * different things. In general, unless a module has been enabled, it's main
     * / basic functions should not work correctly. For a speed controller, this
     * might mean it does not send a signal until it's been enabled.
     *
     * @return returns whether it successfully enabled and is ready to work
     */
    boolean enable();

    /**
     * Returns whether or not the module is ready to work. In most situations,
     * this means that {@link Module#enable()} has been called, but there is no
     * requirement for that to be the case. In fact, it is usually safer to test
     * something else to make sure it has been properly enabled.
     *
     * @return whether the module is ready to work properly
     */
    boolean isEnabled();

    /**
     * A module implementation that lets it be disabled. There is a more strict
     * standard for a {@link DisableableModule}, as disabling should always make
     * {@link DisableableModule#isEnabled()} return false until it is enabled.
     * It should also render the class to function in just as limited of a
     * capacity as it did before being enabled.
     *
     * <p>Disabling an already disabled module should do nothing.
     */
    public static interface DisableableModule extends Module {

        /**
         * "Disables" the module in a way that it returns to the level of
         * functionality before having been enabled. Usually this makes the
         * module unusable for all intents and purposes.
         *
         * @return whether the module successfully disabled
         */
        boolean disable();
    }

    /**
     * This makes the module something that is added to {@link Module#MODULES}
     * when it is constructed. Part of the initial design of this interface
     * (Module was originally a class that was basically equivalent to
     * {@link TrackedModule}), tracked modules make it easy for instances to be
     * tracked by checking mechanisms outside of the module to understand why
     * errors may be occurring. This feature has been changed from mandatory to
     * optional for two specific reasons: <b>1.</b> It is significantly easier
     * to program modules that extend their real underlying counterparts
     * <b>2.</b> Tracking module instances lends itself to unwanted safety
     * hazards from unknown code - it should be opt-in to lend your module
     * object to {@link Module#MODULES}. You don't want people messing with
     * instances of your module.
     *
     * <p> For this reason, use this class sparingly, and understand what
     * extending this class means.
     * 
     * @deprecated is not very safe to use
     */
    public static abstract class TrackedModule implements Module {

        {
            MODULES.add(this);
        }
    }
}