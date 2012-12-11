package edu.ata.gordian.data.strings;

import edu.ata.gordian.Data;
import edu.ata.gordian.StringUtils;
import edu.ata.gordian.data.StringData;

/**
 * String data type that represents concatenated strings. This means a
 * combination of multiple strings together.
 *
 * <p> Ex:
 * <pre> Hello + " World"
 *
 * @author Joel Gallant
 */
public class Concatenation extends StringData {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        // Always check Calculation (specifically addition) before checking this.
        return StringUtils.contains(data, "+");
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
        return new Concatenation(data);
    }

    /**
     * Creates concatenation with the literal string as it appear in the code.
     *
     * @param literalString string in code
     */
    public Concatenation(String literalString) {
        super(literalString);
    }

    /**
     * Returns the actual string value of the concatenation.
     *
     * <p> ex: "Hello" + " World" - > "Hello World"
     *
     * @return concatenated string
     */
    public Object getValue() {
        String value = "";
        String[] parts = StringUtils.split(getLiteralString(), '+');
        for (int x = 0; x < parts.length; x++) {
            value += Data.get(parts[x]).getValue();
        }
        return value;
    }
}