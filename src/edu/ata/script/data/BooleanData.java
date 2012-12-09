package edu.ata.script.data;

import edu.ata.script.Data;
import edu.ata.script.data.booleans.Condition;

/**
 * Data class representing booleans. Is either true or false, just like
 * primitive booleans. Returns a {@link Boolean} object as its value.
 *
 * @author Joel Gallant
 */
public class BooleanData extends Data {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        if (data.equalsIgnoreCase("true") || data.equalsIgnoreCase("false")) {
            return true;
        } else {
            return Condition.isType(data);
        }
    }

    /**
     * Converts the string into a {@link Data} object that is guaranteed to be
     * an instance of this class.
     *
     * <p> Remember to always check {@code isType()} before using this method.
     *
     * @param data string to convert
     * @return {@link Data} object representing string
     */
    public static Data get(String data) {
        if (data.equalsIgnoreCase("true") || data.equalsIgnoreCase("false")) {
            return new BooleanData(data);
        } else if (Condition.isType(data)) {
            return Condition.get(data);
        } else {
            throw new RuntimeException("Could not create boolean with - " + data);
        }
    }

    /**
     * Creates object based off of string. This should only be used for literal
     * booleans.
     *
     * @param literalString string to interpret as string
     */
    public BooleanData(String literalString) {
        super(literalString);
    }

    /**
     * Returns the boolean object representing the string. Returns true if the
     * string is equal to "true", ignoring case.
     *
     * @return boolean object with string.equalsIgnoreCase("true")
     */
    public Object getValue() {
        return java.lang.Boolean.valueOf(getLiteralString().equalsIgnoreCase("true"));
    }
}