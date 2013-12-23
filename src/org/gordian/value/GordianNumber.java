package org.gordian.value;

import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;
import edu.first.util.MathUtils;
import org.gordian.GordianPrimitive;
import org.gordian.method.GordianMethod;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianNumber extends GordianPrimitive {

    public static final Class CLASS = Parent.CLASS;
    private final double val;

    {
        methods().put("neg", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianNumber(-val);
                    }
                });
        methods().put("pow", new GordianMethod(
                new Signature(
                        new Class[]{GordianNumber.CLASS}
                )) {
                    public Object run(Object[] args) {
                        return new GordianNumber(MathUtils.pow(val, ((GordianNumber) args[0]).getValue()));
                    }
                });
        methods().put("sqrt", new GordianMethod(
                new Signature()) {
                    public Object run(Object[] args) {
                        return new GordianNumber(Math.sqrt(val));
                    }
                });
    }

    public GordianNumber(double val) {
        this.val = val;
    }

    public double getValue() {
        return val;
    }

    public int getInt() {
        return (int) val;
    }

    public long getLong() {
        return (long) val;
    }

    public boolean equals(Object object) {
        if (object instanceof GordianNumber) {
            return ((GordianNumber) object).val == val;
        } else {
            return false;
        }
    }

    public Class parentClass() {
        return CLASS;
    }

    public Object parent() {
        return null;
    }

    public String toString() {
        return String.valueOf(val);
    }

    private static final class Parent extends PrimitiveClass {

        private static final Class CLASS = new Parent();
    }
}
