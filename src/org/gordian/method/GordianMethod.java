package org.gordian.method;

import api.gordian.Arguments;
import api.gordian.Object;
import api.gordian.Signature;
import api.gordian.methods.Method;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public abstract class GordianMethod implements Method {

    private final Signature signature;

    public GordianMethod(Signature signature) {
        this.signature = signature;
    }

    public Signature signature() {
        return signature;
    }

    public Object run(Arguments arguments) {
        if (signature.matches(arguments.getSignature())) {
            return run(arguments.getArgs());
        } else {
            throw new RuntimeException("Arguments given did not fit method signature.");
        }
    }

    public abstract Object run(Object[] args);

}
