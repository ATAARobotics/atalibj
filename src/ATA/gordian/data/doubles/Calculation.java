package ATA.gordian.data.doubles;

import ATA.gordian.Data;
import ATA.gordian.data.DoubleData;
import ATA.gordian.data.NumberData;

/**
 * DoubleData subclass that represents all binary calculations. (+, -, *, /) Is
 * always a double (extends {@link DoubleData}).
 *
 * @author Joel Gallant
 */
public abstract class Calculation extends DoubleData {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        return Addition.isType(data)
                || Subtraction.isType(data)
                || Multiplication.isType(data)
                || Division.isType(data);
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
        // Addition and subtraction are first to make sure it goes in order
        // - Division - Multiplication - Subtraction - Addition -
        // This is to give it as close of a resemblance to PEDMAS (DMAS)
        if (Addition.isType(data) || Subtraction.isType(data)) {
            if (data.indexOf('-') < data.indexOf('+') && Subtraction.isType(data)) {
                return Subtraction.get(data);
            } else if (Addition.isType(data)) {
                return Addition.get(data);
            } else {
                return Subtraction.get(data);
            }
        } else if (Multiplication.isType(data) || Division.isType(data)) {
            if (data.indexOf('*') < data.indexOf('/') && Multiplication.isType(data)) {
                return Multiplication.get(data);
            } else if (Division.isType(data)) {
                return Division.get(data);
            } else {
                return Multiplication.get(data);
            }
        } else {
            throw new RuntimeException("Tried to do calculation on non-calculation"
                    + " - " + data);
        }
    }
    private final double num1, num2;

    /**
     * Creates the calculation based on its literal string and operation sign.
     * Is protected because no calculations should use the {@link Calculation}
     * class directly.
     *
     * @param literalString literal string in code
     * @param sign operation sign
     */
    protected Calculation(String literalString, char sign) {
        super(literalString);
        if (literalString.indexOf(sign) == 0) {
            this.num1 = NumberData.doubleValue(
                    literalString.substring(0, literalString.substring(1).indexOf(sign)));
            this.num2 = NumberData.doubleValue(
                    literalString.substring(literalString.substring(1).indexOf(sign) + 1));
        } else {
            this.num1 = NumberData.doubleValue(
                    literalString.substring(0, literalString.indexOf(sign)));
            this.num2 = NumberData.doubleValue(
                    literalString.substring(literalString.indexOf(sign) + 1));
        }
    }

    /**
     * Returns the {@link Double} value of the equation. If it is transferable
     * to an integer (val % 1 == 0), it will transfer to {@link Integer}.
     *
     * @return number value
     */
    public final Object getValue() {
        Double val = doCalc(num1, num2);
        if (val.doubleValue() % 1 == 0) {
            return Integer.valueOf(val.intValue());
        }
        return val;
    }

    /**
     * Returns the value of the equation based on the two terms.
     *
     * @param num1 first term
     * @param num2 second term
     * @return equation result
     */
    protected abstract Double doCalc(double num1, double num2);
}