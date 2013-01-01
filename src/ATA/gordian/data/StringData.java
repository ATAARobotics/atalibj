package ATA.gordian.data;

import ATA.gordian.Data;
import ATA.gordian.StringUtils;
import ATA.gordian.data.strings.Concatenation;

/**
 * Data class representing string values. This class is dangerous due to
 * anything being accepted. There is no way to infer whether or not the data is
 * something else (except by testing beforehand, which is exactly what is done
 * in
 * {@link Data#isType(java.lang.String)} & {@link Data#get(java.lang.String)}).
 * Strings will be equal to {@code literalString.replaceAll("\"","")}, meaning
 * all quotation marks are removed.
 *
 *
 * @author Joel Gallant
 */
public class StringData extends Data {

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        // Should be last resort (test all before this)
        return true;
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
        if (Concatenation.isType(data)) {
            return Concatenation.get(data);
        } else {
            return new StringData(data);
        }
    }
    private final String interpretedString;

    /**
     * Creates string with its literal string value. Removes all quotation
     * marks.
     *
     * @param literalString string value in code
     */
    public StringData(String literalString) {
        super(literalString);
        this.interpretedString = StringUtils.replace(literalString.trim(), '\"', "");
    }

    /**
     * Returns the string value of the data. All quotation marks are removes.
     *
     * @return string value
     */
    public Object getValue() {
        return interpretedString;
    }
}