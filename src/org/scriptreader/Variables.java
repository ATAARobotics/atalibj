package org.scriptreader;

import java.util.Hashtable;

/**
 * Static storage of variables. Stores declared variables.
 *
 * @author joel
 */
public class Variables {

    private static final Hashtable variables = new Hashtable();
    
    /**
     * Adds a variable to the ram.
     *
     * @param name variable name
     * @param value value of the variable
     */
    public static void add(String name, Value value) {
        variables.put(name, value);
    }

    /**
     * Sets a variable in the ram.
     *
     * @param name variable name
     * @param value value of the variable
     */
    public static void set(String name, Value value) {
        variables.put(name, value);
    }

    /**
     * Returns the value of a variable in the ram.
     *
     * @param name variable name
     * @return value of the variable
     */
    public static Value get(String name) {
        return (Value) variables.get(name);
    }

    /**
     * Returns whether the variable is being stored in the ram.
     *
     * @param name variable name
     * @return if the variable exists in the cache
     */
    public static boolean contains(String name) {
        return variables.containsKey(name);
    }
}
