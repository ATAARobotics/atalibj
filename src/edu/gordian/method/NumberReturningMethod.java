package edu.gordian.method;

import edu.gordian.variable.NumberInterface;

public abstract class NumberReturningMethod extends Method implements NumberInterface {

    public NumberReturningMethod(String methodName) {
        super(methodName);
    }

    public final int intValue() {
        return (int) doubleValue();
    }

    public final double doubleValue() {
        return getDouble();
    }

    public final Object getValue() {
        return Double.valueOf(getDouble());
    }

    public abstract double getDouble();
}
