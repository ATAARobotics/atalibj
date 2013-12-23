package org.gordian.scope;

import org.gordian.value.GordianNumber;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianCount extends GordianScope {

    public static void run(GordianScope scope, String s) {
        new GordianCount(scope).run(s.substring(6, s.indexOf(",")),
                s.substring(s.indexOf(",") + 1, s.substring(0, s.indexOf("{")).lastIndexOf(',')),
                s.substring(s.substring(0, s.indexOf("{")).lastIndexOf(',') + 1, s.substring(0, s.indexOf("{")).lastIndexOf(')')),
                s.substring(s.indexOf(";") + 1, s.lastIndexOf('}')));
    }

    public GordianCount(GordianScope scope) {
        super(scope);
    }

    public void run(String name, String from, String to, String run) {
        for (int x = ((GordianNumber) toObject(from)).getInt();
                x <= ((GordianNumber) toObject(to)).getInt(); x++) {
            variables().set(name, new GordianNumber(x));
            run(run);
        }
    }
}
