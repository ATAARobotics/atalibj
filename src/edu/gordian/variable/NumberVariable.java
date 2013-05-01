package edu.gordian.variable;

import edu.gordian.Variable;

public class NumberVariable implements Variable, NumberInterface {

    private final double value;

    public NumberVariable(double value) {
        this.value = value;
    }

    public final int intValue() {
        return (int) value;
    }

    public final double doubleValue() {
        return value;
    }

    public final String toString() {
        return String.valueOf(value);
    }

    public final Object getValue() {
        return Double.valueOf(value);
    }

    public String getLiteralString() {
        return String.valueOf(value);
    }
}
