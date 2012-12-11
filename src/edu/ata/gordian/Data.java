package edu.ata.gordian;

import edu.ata.gordian.data.BooleanData;
import edu.ata.gordian.data.DoubleData;
import edu.ata.gordian.data.IntegerData;
import edu.ata.gordian.data.ReturningMethod;
import edu.ata.gordian.data.StringData;
import edu.ata.gordian.storage.DataStorage;
import edu.ata.gordian.storage.ReturningMethods;

/**
 * Basic framework for all data. In most programming languages (including Java),
 * this concept is mostly known as a data type. It is a representation of
 * something that exists in memory.
 *
 * <p> By default, data is 'dynamic'. This means that when you create a new
 * {@code Data} object, its value is not static (or immutable). The
 * {@code getValue()} method is used each time the value is checked.
 *
 * <p> To convert a plain {@code String} object into a {@code Data} object, use
 * {@link Data#isType(java.lang.String)} and {@link Data#get(java.lang.String)}.
 *
 * <p> All data is accessible through {@link Data#DATA_STORAGE}. Returning
 * Methods (Subclass of {@code Data}) are stored in
 * {@link Data#RETURNING_METHODS}.
 *
 * @author Joel Gallant
 */
public abstract class Data {

    /**
     * Stores all {@link Data} objects.
     *
     * @see DataStorage
     */
    public static final DataStorage DATA_STORAGE = new DataStorage();
    /**
     * Stores all {@link ReturningMethod} objects.
     *
     * @see ReturningMethods
     */
    public static final ReturningMethods RETURNING_METHODS = new ReturningMethods();

    /**
     * Checks to see if the string is transferable to the data type.
     *
     * @param data string to convert
     * @return if string is a boolean
     */
    public static boolean isType(String data) {
        data = data.trim();
        return BooleanData.isType(data)
                || IntegerData.isType(data)
                || DoubleData.isType(data)
                || DATA_STORAGE.contains(data)
                || ReturningMethod.isType(data)
                || StringData.isType(data);
    }

    /**
     * Is tied with {@link Data#isType(java.lang.String)]}. That method should
     * be used before this one.
     *
     * <p> This method returns a {@code Data} object representation of the given
     * string. Every data type is checked, in order of priority.
     *
     * <pre>
     * Boolean
     * Integer
     * Double
     * DataStorage (Saved Data)
     * Returning Methods
     * String
     * </pre>
     *
     * @param data string to convert
     * @return data object representation of string
     * @see Data#isType(java.lang.String)
     */
    public static Data get(String data) {
        data = data.trim();
        /*:)*/ if (BooleanData.isType(data)) {
            return BooleanData.get(data);
        } else if (IntegerData.isType(data)) {
            return IntegerData.get(data);
        } else if (DoubleData.isType(data)) {
            return DoubleData.get(data);
        } else if (DATA_STORAGE.contains(data)) {
            return (Data) DATA_STORAGE.get(data);
        } else if (ReturningMethod.isType(data)) {
            return ReturningMethods.getMethodValue(data);
        } else if (StringData.isType(data)) {
            // Last option because everything is accepted.
            return StringData.get(data);
        } else {
            throw new RuntimeException("Could not parse data - " + data);
        }
    }

    private final String literalString;

    /**
     * Constructor meant <i>only for subclasses</i>. Stores the "literal string"
     * of the data. This means what appears in the code.
     *
     * @param literalString the string in the code that represents the data
     */
    protected Data(String literalString) {
        literalString = literalString.trim();
        this.literalString = literalString;
    }

    /**
     * Returns the string given in the constructor. It should be the same string
     * as found in the code.
     *
     * @return the string in the code that represents the data
     */
    public String getLiteralString() {
        return literalString;
    }

    /**
     * Returns the value of the data. There is no qualifications for what this
     * could return.
     *
     * @return whatever the value of the data is
     */
    public abstract Object getValue();
}