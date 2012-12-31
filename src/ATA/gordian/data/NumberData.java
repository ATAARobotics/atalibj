package ATA.gordian.data;

import ATA.gordian.Data;

/**
 * Data class representing all numbers. The only requirement for number data is
 * to be converted into a double. Ideally, a NumberData class should be a
 * number.
 *
 * @see DoubleData
 * @see IntegerData
 * @author Joel Gallant
 */
public abstract class NumberData extends Data {

    /**
     * The integer of a string representation of data. This method will transfer
     * the string into {@link Data}, and then find its integer value. If the
     * string is not a number, this method will throw a
     * {@link RuntimeException}.
     *
     * @see NumberData#doubleValue(java.lang.String)
     * @param data string representation of integer
     * @return int value of the data
     */
    public static int intValue(String data) {
        return ((NumberData) Data.get(data)).intValue();
    }

    /**
     * The double of a string representation of data. This method will transfer
     * the string into {@link Data}, and then find its double value. If the
     * string is not a number, this method will throw a
     * {@link RuntimeException}.
     *
     * @see NumberData#intValue(java.lang.String)
     * @param data string representation of double
     * @return double value of the data
     */
    public static double doubleValue(String data) {
        return ((NumberData) Data.get(data)).doubleValue();
    }

    /**
     * Creates data with the literal string. Just redirects to
     * {@link Data#Data(java.lang.String)}.
     *
     * @param data literal value of the data
     */
    public NumberData(String data) {
        super(data);
    }

    /**
     * Returns the double value of the number. Is abstract to be used by the
     * subclass.
     *
     * @return double value of data
     */
    public abstract double doubleValue();

    /**
     * Returns the int value of the number. Is equivalent to
     * {@code (int) doubleValue()}. Does not round the number (Uses floor).
     *
     * @return int value of the number
     */
    public int intValue() {
        return (int) doubleValue();
    }
}