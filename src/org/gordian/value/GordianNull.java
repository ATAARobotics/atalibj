package org.gordian.value;

import api.gordian.Class;
import api.gordian.Object;
import org.gordian.GordianPrimitive;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianNull extends GordianPrimitive {

    public static final Class CLASS = Parent.CLASS;

    public boolean equals(Object object) {
        return object instanceof GordianNull;
    }

    public Class parentClass() {
        return CLASS;
    }

    public Object parent() {
        return null;
    }

    public String toString() {
        return "null";
    }


    private static final class Parent extends PrimitiveClass {

        private static final Class CLASS = new Parent();
    }
}
