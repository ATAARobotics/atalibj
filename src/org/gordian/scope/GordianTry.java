package org.gordian.scope;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianTry extends GordianScope {

    public static void run(GordianScope scope, String s) {
        GordianTry gt = new GordianTry(scope);
        
        char[] c = s.toCharArray();
        int track = 0;
        int catchIndex = -1;
        for (int x = s.indexOf("{"); x < c.length; x++) {
            if (c[x] == '{') {
                track++;
            } else if (c[x] == '}') {
                track--;
            }
            if (track == 0) {
                if(s.substring(x).startsWith("}catch{")) {
                    catchIndex = x;
                }
                break;
            }
        }
        if (catchIndex >= 0) {
            gt.runWithCatch(s.substring(s.indexOf("{") + 1, catchIndex),
                    s.substring(catchIndex + 7, s.lastIndexOf('}')));
        } else {
            gt.runWithoutCatch(s.substring(s.indexOf("{") + 1, s.lastIndexOf('}')));
        }
    }

    public GordianTry(GordianScope container) {
        super(container);
    }

    public void runWithCatch(String t, String c) {
        try {
            super.run(t);
        } catch (Exception ex) {
            super.run(c);
        }
    }

    public void runWithoutCatch(String t) {
        try {
            super.run(t);
        } catch (Exception ex) {
        }
    }
}
