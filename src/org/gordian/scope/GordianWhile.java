package org.gordian.scope;

import org.gordian.value.GordianBoolean;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianWhile extends GordianScope {

    public static void run(GordianScope scope, String s) {
        new GordianWhile(scope).run(s.substring(6, s.substring(0, s.indexOf("{")).lastIndexOf(')')),
                s.substring(s.indexOf(";") + 1, s.lastIndexOf('}')));
    }

    public GordianWhile(GordianScope scope) {
        super(scope);
    }

    public void run(String cond, String run) {
        while (((GordianBoolean) toObject(cond)).getValue()) {
            run(run);
        }
    }
}
