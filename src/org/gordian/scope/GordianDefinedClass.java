package org.gordian.scope;

import api.gordian.Arguments;
import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;
import api.gordian.methods.Method;
import api.gordian.storage.InternalNotFoundException;
import org.gordian.GordianClass;
import org.gordian.method.GordianMethod;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianDefinedClass extends GordianScope implements Class {

    public static GordianDefinedClass get(GordianScope container, Class parent, String internals) {
        return new GordianDefinedClass(parent, findConstructors(internals), internals, container);
    }

    private final Class parent;
    private final String[] constructors;
    private final String internals;

    public GordianDefinedClass(Class parent, String[] constructors, String internals, GordianScope container) {
        super(container);
        this.parent = parent;
        this.constructors = constructors;
        this.internals = internals;
    }

    private boolean superCalled = false;

    public Object contruct(Arguments arguments) {
        final GordianDefinedInstance instance = new GordianDefinedInstance(this);
        superCalled = false;

        if (parent != null) {
            // give access to super
            Signature[] parentConstructors = parent.contructors();
            for (int x = 0; x < parentConstructors.length; x++) {
                instance.methods().put("super", new GordianMethod(parentConstructors[x]) {
                    public Object run(Object[] args) {
                        // sorry, cannot inherit from non-GordianScope
                        GordianScope o = (GordianScope) parent.contruct(new Arguments(args));
                        instance.inheritFrom(o);
                        superCalled = true;
                        return o;
                    }
                });
            }
        }

        instance.run(internals);

        if (constructors.length == 0) {
            instance.methods().put("construct", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    // run super when available
                    if (parent != null) {
                        try {
                            instance.methods().get("super").run(new Arguments());
                        } catch (InternalNotFoundException ex) {
                            throw new RuntimeException("Empty super not found - must declare super manually");
                        }
                    }
                    return null;
                }
            });
        }

        // make constructors
        for (int x = 0; x < constructors.length; x++) {
            instance.run(constructors[x]);
        }

        // run correct constructor
        boolean constructed = false;
        Method[] m = instance.methods().getAll("construct");
        for (int x = 0; x < m.length; x++) {
            if (m[x].signature().matches(arguments.getSignature())) {
                m[x].run(arguments);
                constructed = true;
                break;
            }
        }

        if (!constructed) {
            throw new RuntimeException("Class did not have any matching constructors.");
        }
        if (!superCalled && parent != null) {
            try {
                // lazy people get free super construction after constructor
                instance.methods().get("super").run(new Arguments());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Super constructor was not called");
            }
        }

        // take away access to super
        instance.methods().removeAll("super");

        // get rid of construct
        instance.methods().removeAll("construct");

        return instance;
    }

    public Signature[] contructors() {
        if (constructors.length == 0) {
            return new Signature[]{new Signature()};
        }
        Signature[] s = new Signature[constructors.length];
        for (int x = 0; x < s.length; x++) {
            String con = constructors[x].substring(0, constructors[x].indexOf(";"));
            String[] args = getPureArgs(con.substring(con.indexOf('(') + 1, con.lastIndexOf(')')));
            Class[] c = new Class[args.length];
            for (int i = 0; i < c.length; i++) {
                c[i] = GordianClass.ALL_CLASSES;
            }
            s[x] = new Signature(c);
        }
        return s;
    }

    private final class GordianDefinedInstance extends GordianScope implements Object {

        public GordianDefinedInstance(GordianScope container) {
            super(container);
        }

        public boolean equals(Object object) {
            return this == object;
        }

        public Class parentClass() {
            return GordianDefinedClass.this;
        }

    }
}
