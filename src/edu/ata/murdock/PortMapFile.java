package edu.ata.murdock;

import edu.first.utils.Logger;
import edu.gordian.StringUtils;
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
    private static final PortMapFile PORT_MAP_FILE = new PortMapFile();
    private static Port[] ports;

    static {
        try {
            String[] p = ports();
            ports = new Port[p.length];
            for (int x = 0; x < ports.length; x++) {
                ports[x] = new Port(p[x].substring(0, p[x].indexOf("=")).trim(), 
                        Integer.parseInt(p[x].substring(p[x].indexOf("=") + 1).trim()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.log(Logger.Urgency.USERMESSAGE, "PortMap not found");
        }
    }

    /**
     * Returns the singleton instance of PortMapFile. When this is called, the
     * information from the file is usually retrieved.
     *
     * @return singleton instance
     */
    public static PortMapFile getInstance() {
        return PORT_MAP_FILE;
    }

    /**
     * Returns an array of all the ports available in the text file.
     *
     * @return all ports found
     */
    public static Port[] getAllPorts() {
        return ports;
    }

    /**
     * Returns the port number saved under a name.
     *
     * @param name string before "=" in the text file
     * @param def default if the port does not exist
     * @return port number in the text file or the default given
     */
    public static int getPort(String name, int def) {
        for (int x = 0; x < ports.length; x++) {
            if (ports[x].name.equals(name)) {
                Logger.log(Logger.Urgency.LOG, name + " is " + ports[x].port);
                return ports[x].port;
            }
        }
        Logger.log(Logger.Urgency.USERMESSAGE, "Port " + name + " was not found in PortMap.txt, using " + def);
        return def;
    }

    private static String[] ports() throws IOException {
        return StringUtils.split(Logger.getTextFromFile(Connector.openDataInputStream(PATH)), '\n');
    }

    private static final class Port {

        public final String name;
        public final int port;

        public Port(String name, int port) {
            this.name = name;
            this.port = port;
        }
    }
}