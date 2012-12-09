package edu.ata.script.data.doubles;

import edu.ata.script.Data;
import edu.ata.script.StringUtils;
import edu.ata.script.data.NumberData;

/**
 * Subset of {@link Calculation}, which adds two nodes together.
 *
 * @author Joel Gallant
 */
public class Addition extends Calculation {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        if (!StringUtils.contains(data, "+")) {
            return false;
        }
        if (Data.isType(data.substring(0, data.indexOf('+')))
                && Data.isType(data.substring(data.indexOf('+') + 1))) {
            Data d1 = Data.get(data.substring(0, data.indexOf('+'))),
                    d2 = Data.get(data.substring(data.indexOf('+') + 1));
            return (d1 instanceof NumberData)
                    && (d2 instanceof NumberData);
        } else {
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
        return new Addition(data);
    }

    /**
     * Creates addition with the literal string as it shows up in the code.
     *
     * @param literalString string in code
     */
    public Addition(String literalString) {
        super(literalString, '+');
    }

    /**
     * Does the addition of {@code num1} and {@code num2}.
     *
     * @param num1 first node
     * @param num2 second node
     * @return num1 + num2
     */
    protected Double doCalc(double num1, double num2) {
        return Double.valueOf(num1 + num2);
    }
}