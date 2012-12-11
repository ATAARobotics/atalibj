package edu.ata.gordian.data;

import edu.ata.gordian.Data;
import edu.ata.gordian.StringUtils;

/**
 * Data class representing all methods that return values.
 *
 * @see Data#RETURNING_METHODS for storage of methods
 * @author Joel Gallant
 */
public abstract class ReturningMethod {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        if (!StringUtils.contains(data, "(") || !StringUtils.contains(data, ")")) {
            return false;
        }
        return Data.RETURNING_METHODS.contains(data.substring(0, data.indexOf("(")));
    }

    /**
     * Returns the value of the method according to its arguments. {@code args}
     * will be Data[0] if there are no arguments.
     *
     * @param args arguments given within "(" and ")"
     * @return value of the method
     */
    public abstract Data getValue(Data[] args);
}
