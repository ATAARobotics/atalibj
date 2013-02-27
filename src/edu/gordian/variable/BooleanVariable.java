package edu.gordian.variable;

import edu.gordian.Variable;

public class BooleanVariable implements Variable, BooleanInterface {

    private final boolean value;

    public BooleanVariable(boolean value) {
        this.value = value;
    }

    public final boolean booleanValue() {
        return value;
    }

    public final Object getValue() {
        return Boolean.valueOf(value);
    }

    public String getLiteralString() {
        return String.valueOf(value);
    }

    public String toString() {
        return getValue().toString();
    }
}
