package edu.ATA.autonomous;

import ATA.gordian.Script;
import edu.ATA.main.Logger;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Gordian {

    private static boolean init = false;

    private Gordian() {
    }

    public static void ensureInit() {
        if (!init) {
            init();
            init = true;
        }
    }
    
    public static void run(String fileName) throws IOException {
        ensureInit();
        String script = Logger.getTextFromFile(Connector.openDataInputStream("file:///"+fileName));
        Script.run(script);
    }

    private static void init() {
    }
}
