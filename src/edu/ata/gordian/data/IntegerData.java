package edu.ata.gordian.data;

import edu.ata.gordian.Data;
import edu.ata.gordian.data.doubles.Calculation;

/**
 * Data class that represents integers in the code. It is usually rare for
 * numbers to be integers, due to all calculations returning doubles.
 * (statically, {@link Calculation} is a {@link DoubleData})
 *
 * @author Joel Gallant
 */
public class IntegerData extends NumberData {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        try {
            java.lang.Integer.valueOf(data);
            return true;
        } catch (NumberFormatException ex) {
            return false;
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
        try {
            java.lang.Integer.valueOf(data);
            return new IntegerData(data);
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Could not create integer with - " + data);
        }
    }

    /**
     * Creates integer object with the string. Should only be used for literal
     * integers.
     *
     * @param literalString string in code
     */
    public IntegerData(String literalString) {
        super(literalString);
    }

    /**
     * Primitive double value of the integer. Required by {@link NumberData}.
     *
     * @return primitive double of integer
     */
    public double doubleValue() {
        return ((java.lang.Integer) getValue()).doubleValue();
    }

    /**
     * Returns a {@link Integer} equivalent to the value of the integer.
     *
     * @return {@link Integer} object equal to data
     */
    public Object getValue() {
        return java.lang.Integer.valueOf(getLiteralString());
    }
}