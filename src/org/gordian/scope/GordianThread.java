package org.gordian.scope;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GordianThread extends GordianScope {

    public static void run(GordianScope scope, String s) {
        new GordianThread(scope).runThread(s.substring(s.indexOf("{") + 1, s.lastIndexOf('}')));
    }
    
    public GordianThread(GordianScope container) {
        super(container);
    }

    public void runThread(final String run) {
        new Thread(new Runnable() {
            public void run() {
                GordianThread.this.run(run);
            }
        }).start();
    }
}
