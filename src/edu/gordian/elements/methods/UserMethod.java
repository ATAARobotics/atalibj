package edu.gordian.elements.methods;

import edu.gordian.Strings;

/**
 * The class for users to extend that provides Gordian methods. A user method
 * has a name and a runnable instance.
 *
 * @author Joel Gallant
 */
public abstract class UserMethod implements MethodBase {

    private final String name;

    /**
     * Constructs the user method.
     *
     * @param name name of method before parentheses
     */
    public UserMethod(String name) {
        if (name == null || Strings.isEmpty(name) || Strings.contains(name, '(') || Strings.contains(name, ')')) {
            throw new IllegalArgumentException("Name given was invalid");
        }
        this.name = name;
    }

    /**
     * Returns the name of the method.
     *
     * @return name before parentheses
     */
    public final String getName() {
        return name;
    }
}
