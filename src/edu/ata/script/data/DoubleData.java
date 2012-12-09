package edu.ata.script.data;

import edu.ata.script.Data;
import edu.ata.script.data.doubles.Calculation;

/**
 * Data class representing all double values. The value is stored as a
 * {@link Double} object.
 *
 * @author Joel Gallant
 */
public class DoubleData extends NumberData {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        try {
            java.lang.Double.valueOf(data);
            return true;
        } catch (NumberFormatException ex) {
            return Calculation.isType(data);
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
            java.lang.Double.valueOf(data);
            return new DoubleData(data);
        } catch (NumberFormatException ex) {
            if (Calculation.isType(data)) {
                return Calculation.get(data);
            } else {
                throw new RuntimeException("Could not create double with - " + data);
            }
        }
    }

    /**
     * Returns the primitive double value of the data.
     *
     * @return primitive double value
     */
    public double doubleValue() {
        return ((java.lang.Double) getValue()).doubleValue();
    }

    /**
     * Creates object with the string. Should only be used for literal doubles.
     *
     * @param literalString string in code
     */
    public DoubleData(String literalString) {
        super(literalString);
    }

    /**
     * Returns a {@link Double} object equivalent to the value of the double.
     *
     * @return {@link Double} object of value
     */
    public Object getValue() {
        return java.lang.Double.valueOf(getLiteralString());
    }
}