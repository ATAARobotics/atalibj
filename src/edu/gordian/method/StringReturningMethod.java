package edu.gordian.method;

import edu.gordian.variable.StringInterface;

public abstract class StringReturningMethod extends Method implements StringInterface {

    public StringReturningMethod(String methodName) {
        super(methodName);
    }

    public final String stringValue() {
        return getString();
    }

    public Object getValue() {
        return getString();
    }

    public abstract String getString();

    public String toString() {
        return getString();
    }
}
