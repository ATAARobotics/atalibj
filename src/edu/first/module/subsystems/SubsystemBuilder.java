package edu.first.module.subsystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.first.module.Module;

/**
 * A builder class for subsystems, so that the user can dynamically add modules
 * to the subsystem instead of being statically attached to the group of
 * modules. This class does not perform nearly as well as {@link Subsystem}
 * because it is mutable. Only use it when needed.
 *
 * @since June 19 13
 * @author Joel Gallant
 */
public final class SubsystemBuilder {

    private final List<Module> modules = new ArrayList<>();
    private Subsystem subsystem;
    private boolean changed;

    /**
     * Constructs an empty builder that can accept modules.
     */
    public SubsystemBuilder() {
    }

    /**
     * Constructs a builder with elements already added.
     *
     * @param modules elements to add automatically
     */
    public SubsystemBuilder(Module[] modules) {
        Collections.addAll(this.modules, modules);
    }

    /**
     * Adds a module to the subsystem.
     *
     * @param module module to be part of the subsystem
     * @return the current builder
     */
    public SubsystemBuilder add(Module module) {
        modules.add(module);
        changed = true;
        return this;
    }

    /**
     * Removes a module from the subsystem.
     *
     * @param module module to remove from the subsystem
     * @return the current builder
     */
    public SubsystemBuilder remove(Module module) {
        modules.remove(module);
        changed = true;
        return this;
    }

    /**
     * Returns an immutable {@link Subsystem} instance with all the currently
     * held modules in this builder.
     *
     * @return subsystem built with this class
     */
    public Subsystem toSubsystem() {
        if (changed) {
            Object[] b = modules.toArray();
            Module[] mod = new Module[b.length];
            System.arraycopy(b, 0, mod, 0, b.length);
            subsystem = new Subsystem(mod);
        }
        return subsystem;
    }
}
