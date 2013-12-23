package org.gordian;

import api.gordian.Arguments;
import org.gordian.storage.GordianVariables;
import org.gordian.storage.GordianMethods;
import api.gordian.Object;
import api.gordian.Signature;
import api.gordian.storage.Methods;
import api.gordian.storage.Variables;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public abstract class GordianPrimitive implements Object {

    private final Methods methods = new GordianMethods(true);
    private final Variables variables = new GordianVariables(true);

    public Methods methods() {
        return methods;
    }

    public Variables variables() {
        return variables;
    }

    protected static class PrimitiveClass extends GordianClass {

        public PrimitiveClass() {
            super(null);
        }

        public Object contruct(Arguments arguments) {
            return null;
        }

        public Signature[] contructors() {
            return null;
        }

    }
}
