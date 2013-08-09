package edu.gordian.values;

import edu.gordian.values.gordian.GordianBoolean;
import edu.gordian.values.gordian.GordianNumber;
import edu.gordian.values.gordian.GordianString;

/**
 * Utility class for values.
 *
 * @author Joel Gallant
 */
public final class Values {

    /**
     * The official object representation of empty values created with
     * {@code make}.
     */
    public static final Object EMPTY = new Object();

    private Values() {
    }

    /**
     * Returns a {@link Value} that {@link Value#getValue()} will always return
     * {@code o}.
     *
     * @param o internal value of the new value object
     * @return value with static internal value
     */
    public static Value literal(final Object o) {
        return new Value() {
            public Object getValue() {
                return o;
            }
        };
    }

    /**
     * Returns an empty value with a blank object as the internal value.
     *
     * @return empty value
     */
    public static Value emptyValue() {
        return literal(EMPTY);
    }

    /**
     * Returns whether the string is a literal representation of a boolean,
     * number or string.
     *
     * @param v string to test
     * @return if string is a literal primitive value
     */
    public static boolean isLiteralValue(String v) {
        return GordianBoolean.is(v) || GordianNumber.is(v) || GordianString.is(v);
    }

    /**
     * Returns the literal representation of the string.
     *
     * @param v string to convert
     * @return literal primitive value
     */
    public static Value literalValue(String v) {
        if (GordianBoolean.is(v)) {
            return GordianBoolean.valueOf(v);
        } else if (GordianNumber.is(v)) {
            return GordianNumber.valueOf(v);
        } else if (GordianString.is(v)) {
            return GordianString.valueOf(v);
        } else {
            throw new RuntimeException(v + " is not a literal value.");
        }
    }
}
