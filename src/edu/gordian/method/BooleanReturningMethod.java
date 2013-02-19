package edu.gordian.method;

import edu.gordian.method.Method;
import edu.gordian.variable.BooleanInterface;

public abstract class BooleanReturningMethod extends Method implements BooleanInterface {

    public BooleanReturningMethod(String methodName) {
        super(methodName);
    }

    public final boolean booleanValue() {
        return getBoolean();
    }

    public final Object getValue() {
        return Boolean.valueOf(getBoolean());
    }

    public abstract boolean getBoolean();

    public String toString() {
        return String.valueOf(getBoolean());
    }
}
