package ATA.gordian.data.doubles;

import ATA.gordian.Data;
import ATA.gordian.StringUtils;
import ATA.gordian.data.NumberData;

/**
 * Subset of {@link Calculation} that divides two numbers.
 *
 * @author Joel Gallant
 */
public class Division extends Calculation {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        if (!StringUtils.contains(data, "/")) {
            return false;
        }
        if (Data.isType(data.substring(0, data.indexOf('/')))
                && Data.isType(data.substring(data.indexOf('/') + 1))) {
            Data d1 = Data.get(data.substring(0, data.indexOf('/'))),
                    d2 = Data.get(data.substring(data.indexOf('/') + 1));
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
        return new Division(data);
    }

    /**
     * Creates division based on literal string in code.
     *
     * @param literalString string in code
     */
    public Division(String literalString) {
        super(literalString, '/');
    }

    /**
     * Divides num1 by num2.
     *
     * @param num1 first term
     * @param num2 second term
     * @return {@link Double} value of num1 / num2
     */
    protected Double doCalc(double num1, double num2) {
        return Double.valueOf(num1 / num2);
    }
}