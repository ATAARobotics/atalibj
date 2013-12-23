package org.gordian.scope;

import api.gordian.Arguments;
import api.gordian.Class;
import api.gordian.Object;
import api.gordian.Signature;
import api.gordian.methods.Method;
import api.gordian.methods.ValueReturned;
import org.gordian.GordianClass;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianDefinedMethod extends GordianScope implements Method {

    public static GordianDefinedMethod get(GordianScope scope, String s) {
        return new GordianDefinedMethod(scope, scope.getPureArgs(s.substring(s.indexOf('(') + 1, s.substring(0, s.indexOf("{")).lastIndexOf(')'))),
                s.substring(s.indexOf("{") + 1, s.lastIndexOf('}')));
    }

    private final String run;
    private final String[] args;

    public GordianDefinedMethod(GordianScope container, String[] args, String run) {
        super(container);
        this.run = run;
        this.args = args;
    }

    public Signature signature() {
        Class[] c = new Class[args.length];
        for (int x = 0; x < c.length; x++) {
            // no type-safe arguments
            c[x] = GordianClass.ALL_CLASSES;
        }
        return new Signature(c);
    }

    public Object run(Arguments arguments) {
        if (!signature().matches(arguments.getSignature())) {
            throw new RuntimeException("Arguments did not match method requirements - " + arguments);
        }
        
        Object[] o = arguments.getArgs();
        for(int x = 0; x < args.length; x++) {
            variables().put(args[x], o[x]);
        }
        try {
            run(run);
        } catch (ValueReturned e) {
            return e.value;
        }
        return null;
    }

}
