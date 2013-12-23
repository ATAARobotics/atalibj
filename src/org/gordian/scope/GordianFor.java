package org.gordian.scope;

import org.gordian.value.GordianNumber;

public class GordianFor extends GordianScope {

    public static void run(GordianScope scope, String s) {
        new GordianFor(scope).run(s.substring(4, s.substring(0, s.indexOf("{")).lastIndexOf(')')),
                s.substring(s.indexOf(";") + 1, s.lastIndexOf('}')));
    }

    public GordianFor(GordianScope container) {
        super(container);
    }

    public void run(String i, String run) {
        for (int x = 0; x < ((GordianNumber) toObject(i)).getInt(); x++) {
            run(run);
        }
    }
}
