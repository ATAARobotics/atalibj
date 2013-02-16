package edu.first.module.subsystem;

import edu.first.module.Module;

/**
 * A class that enables modules to use modules within itself, and enable/disable
 * them at the same time as itself. This eliminates the need for a lot of
 * boilerplate code enabling and disabling "sub-modules". As a general
 * precaution though, if you extend this class (Class A), it will be very
 * difficult to extend Class A if the extending class (Class B) has more modules
 * to enable. Instead of extending, it is typically advisable to use composition
 * to keep modules enabling and disabling with their subsystem.
 *
 * <p> EX: A -> B <p> B -> C
 *
 * <p> C(modules) !> A <p> C(modules) are never enabled
 *
 * @author Joel Gallant
 */
public class Subsystem implements Module.DisableableModule {

    private final Module[] modules;

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
     * Disables all modules that were submitted in
     * {@link Subsystem#Subsystem(edu.ATA.module.Module[])}.
     *
     * @return if there are modules disabled
     */
    public boolean disable() {
        for (int x = 0; x < modules.length; x++) {
            if (modules[x] instanceof DisableableModule) {
                ((DisableableModule) modules[x]).disable();
            }
        }
        return !isEnabled();
    }

    /**
     * Enables all modules that were submitted in
     * {@link Subsystem#Subsystem(edu.ATA.module.Module[])}.
     *
     * @return if all modules are enabled
     */
    public  boolean enable() {
        for (int x = 0; x < modules.length; x++) {
            modules[x].enable();
        }
        return isEnabled();
    }

    /**
     * Checks if all modules are enabled.
     *
     * @return if all modules are enabled
     */
    public boolean isEnabled() {
        for (int x = 0; x < modules.length; x++) {
            if (!modules[x].isEnabled()) {
                return false;
            }
        }
        return true;
    }
}
