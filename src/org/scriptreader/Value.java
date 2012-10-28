package org.scriptreader;

import org.scriptreader.instruction.Method;
import org.scriptreader.instruction.Returnables;
import org.scriptreader.values.Condition;

/**
 * An implicitly casted value, used everywhere that requires a value.
 *
 * @author joel
 */
public class Value {

    private final Object value;
    private final String literalValue;

    /**
     * Creates the value, and implicitly casts it.
     *
     * <p>Uses the least specific, but precise enough value type to store the
     * data.</p> <p>Goes in the order:</p> <p>
     * <pre>    1.Variables</pre>
     * <pre>    2.Returnable Method</pre>
     * <pre>    3.Integer</pre>
     * <pre>    4.Double</pre>
     * <pre>    5.Boolean (Literal and Condition)</pre>
     * <pre>    6.String</pre>
     *
     * @param value
     */
    public Value(String value) {
        this.literalValue = value;
        if (Variables.contains(value)) {
            this.value = Variables.get(value).getValue();
        } else if (Returnables.isValid(value) && Returnables.contains(Method.getMethodName(value))) {
            this.value = Returnables.getFromMethod(value);
        } else if (isInteger(value)) {
            this.value = new Integer(Integer.parseInt(value));
        } else if (isDouble(value)) {
            this.value = new Double(Double.parseDouble(value));
        } else if (isBoolean(value)) {
            this.value = (value.equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE);
        } else if (Condition.isCondition(value)){
            this.value = (Condition.getConditionFrom(value).isTrue() ? Boolean.TRUE : Boolean.FALSE);
        } else {
            if (StringUtils.contains(value, "\"")) {
                this.value = StringUtils.replace(value, '\"', "");
            } else {
                this.value = value;
            }
        }
    }

    /**
     * Returns the actual value of the {@link Value}.
     *
     * @return the actual value
     */
    public Object getValue() {
        return value;
    }

    public String toString() {
        return getValue().toString();
    }

    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Value) {
            return this.value.equals(((Value) obj).value);
        } else {
            return false;
        }
    }

    /**
     * Returns the String value that the value started with.
     *
     * @return literal value of original value
     */
    public String getLiteralValue() {
        return literalValue;
    }

    /**
     * Class method to check if it is possible to transfer a string to an
     * Integer.
     *
     * @param value string to transfer
     * @return whether string can be transferred
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Class method to check if it is possible to transfer a string to an
     * Double.
     *
     * @param value string to transfer
     * @return whether string can be transferred
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Class method to check if it is possible to transfer a string to an
     * Boolean.
     *
     * @param value string to transfer
     * @return whether string can be transferred
     */
    public static boolean isBoolean(String value) {
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }
}
