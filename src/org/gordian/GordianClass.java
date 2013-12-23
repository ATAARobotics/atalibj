package org.gordian;

import api.gordian.Arguments;
import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public abstract class GordianClass extends GordianPrimitive implements Class {

    public static final GordianClass ALL_CLASSES = new GordianClass(null) {

        public Object contruct(Arguments arguments) {
            return null;
        }

        public Signature[] contructors() {
            return null;
        }

        public boolean equals(Object object) {
            return true;
        }

    };
    private final Class parent;

    public GordianClass(Class parent) {
        this.parent = parent;
    }

    public abstract Object contruct(Arguments arguments);

    public abstract Signature[] contructors();

    public boolean equals(Object object) {
        if (!(object instanceof Class)) {
            return false;
        }
        Class c = (Class) object;
        while (c != null) {
            if (this == c) {
                return true;
            }
            c = c.parentClass();
        }
        return false;
    }

    public Class parentClass() {
        return parent;
    }

}
