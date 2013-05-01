package edu.gordian.method;

import edu.gordian.Element;

public abstract class Method implements Element {

    private final String methodName;

    Method(String methodName) {
        this.methodName = methodName;
    }

    public final String getLiteralString() {
        return methodName + "()";
    }

    public final String getMethodName() {
        return methodName;
    }
}
