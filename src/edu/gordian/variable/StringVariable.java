package edu.gordian.variable;

import edu.gordian.Variable;

public class StringVariable implements Variable, StringInterface {

    private final String value;

    public StringVariable(java.lang.String value) {
        this.value = value;
    }
    
    public final String stringValue() {
        return value;
    }

    public final Object getValue() {
        return value;
    }

    public String getLiteralString() {
        return value;
    }

    public String toString() {
        return getValue().toString();
    }
}
