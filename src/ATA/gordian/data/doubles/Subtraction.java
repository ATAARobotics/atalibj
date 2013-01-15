package ATA.gordian.data.doubles;

import ATA.gordian.Data;
import ATA.gordian.data.NumberData;

/**
 * Subset of {@link Calculation}, which subtracts one value from the other.
 *
 * @author Joel Gallant
 */
public class Subtraction extends Calculation {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        if (data.indexOf('-') == 0) {
            data = data.substring(1);
        }
        if (data.indexOf('-') < 0) {
            return false;
        }
        if (Data.isType(data.substring(0, data.indexOf('-')))
                && Data.isType(data.substring(data.indexOf('-') + 1))) {
            Data d1 = Data.get(data.substring(0, data.indexOf('-'))),
                    d2 = Data.get(data.substring(data.indexOf('-') + 1));
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
        return new Subtraction(data);
    }

    /**
     * Creates subtraction based on its literal value found in the code.
     *
     * @param literalString string in code
     */
    public Subtraction(String literalString) {
        super(literalString, '-');
    }

    /**
     * Does the subtraction of num2 from num1.
     *
     * @param num1 first term
     * @param num2 second term
     * @return {@link Double} value of num1 - num2
     */
    protected Double doCalc(double num1, double num2) {
        return Double.valueOf(num1 - num2);
    }
}