package edu.ata.murdock;

import com.sun.squawk.microedition.io.FileConnection;
import com.sun.squawk.util.StringTokenizer;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 * Singleton instance giving access to /PortMap.txt. Retrieves the information
 * when it is statically initialized.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class PortMapFile {

    /**
     * Path to the port map file.
     */
    public static final String PATH = "file:///PortMap.txt";
    private static PortMapFile PORT_MAP_FILE;
    private Port[] ports;

    private PortMapFile() {
        long start = System.currentTimeMillis();
        while (Preferences.getInstance().getKeys().size() < 1) {
            System.out.println("Waiting for preferences...");
            Timer.delay(0.02);
            if (System.currentTimeMillis() - start > 10000) {
                Logger.log(Logger.Urgency.USERMESSAGE, "Preferences took too long to initialize -  using default ports!!!");
                ports = new Port[0];
                return;
            }
        }
        try {
            String[] p = ports();
            ports = new Port[p.length];
            for (int x = 0; x < ports.length; x++) {
                String name = p[x].substring(0, p[x].indexOf("=")).trim();
                String num = p[x].substring(p[x].indexOf("=") + 1).trim();
                Logger.log(Logger.Urgency.LOG, name + " is " + num);
                ports[x] = new Port(name, Integer.parseInt(num));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.log(Logger.Urgency.USERMESSAGE, "PortMap not found");
        }

        Logger.log(Logger.Urgency.LOG, "Done loading port map");
    }

    /**
     * Returns the singleton instance of PortMapFile. When this is called, the
     * information from the file is usually retrieved.
     *
     * @return singleton instance
     */
    public static PortMapFile getInstance() {
        if (PORT_MAP_FILE == null) {
            PORT_MAP_FILE = new PortMapFile();
        }
        return PORT_MAP_FILE;
    }

    /**
     * Returns an array of all the ports available in the text file.
     *
     * @return all ports found
     */
    public Port[] getAllPorts() {
        return ports;
    }

    /**
     * Returns the port number saved under a name.
     *
     * @param name string before "=" in the text file
     * @param def default if the port does not exist
     * @return port number in the text file or the default given
     */
    public int getPort(String name, int def) {
        for (int x = 0; x < ports.length; x++) {
            if (ports[x].name.equals(name)) {
                Logger.log(Logger.Urgency.LOG, "Port " + name + " found - " + ports[x].port);
                return ports[x].port;
            }
        }
        Logger.log(Logger.Urgency.USERMESSAGE, "Port " + name + " was not found in PortMap.txt, using " + def);
        return def;
    }

    private String[] ports() throws IOException {
        FileConnection connection = (FileConnection) Connector.open(PATH, Connector.READ);
        String file = Logger.getTextFromFile(connection);
        connection.close();
        StringTokenizer tokenizer = new StringTokenizer(file);
        String[] s = new String[tokenizer.countTokens()];
        int x = 0;
        while (tokenizer.hasMoreElements()) {
            s[x++] = tokenizer.nextToken();
        }
        return s;
    }

    /**
     *
     */
    public static final class Port {

        /**
         *
         */
        public final String name;
        /**
         *
         */
        public final int port;

        /**
         *
         * @param name
         * @param port
         */
        public Port(String name, int port) {
            this.name = name;
            this.port = port;
        }
    }
}