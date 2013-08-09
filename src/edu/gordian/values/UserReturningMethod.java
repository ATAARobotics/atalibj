package edu.gordian.values;

import edu.gordian.Strings;

/**
 * The class for users to extend that provides Gordian returning methods. A user
 * method has a name and a runnable instance.
 *
 * @author Joel Gallant
 */
public abstract class UserReturningMethod implements ReturningMethodBase {

    private final String name;

    /**
     * Constructs the user method.
     *
     * @param name name of method before parentheses
     */
    public UserReturningMethod(String name) {
        if (name == null || Strings.isEmpty(name)) {
            throw new IllegalArgumentException("Name given was invalid");
        }
        this.name = name;
    }

    /**
     * Returns the name of the method.
     *
     * @return name before parentheses
     */
    public String getName() {
        return name;
    }
}
