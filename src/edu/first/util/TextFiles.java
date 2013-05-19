package edu.first.util;

import com.sun.squawk.io.BufferedWriter;
import com.sun.squawk.microedition.io.FileConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.microedition.io.Connector;

/**
 * A set of utility methods to manipulate text files. Behavior of IO is
 * documented in Javadocs.
 *
 * @since May 14 13
 * @author Joel Gallant
 */
public final class TextFiles {

    // cannot be subclassed or instantiated
    private TextFiles() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Returns the text that is currently in a text file. Does not assume ".txt"
     * ending, so file type must be added manually.
     *
     * All IO error are caught and logged <i>inside</i> of this method, as they
     * are assumed to be things that are unaccountable for (hardware issues).
     *
     * If an IO error occurs, this method returns null. That is the only
     * occasion that this method will return null, so it is safe to assume that
     * null means an error occurred.
     *
     * @param fileName file name from the root directory in the cRIO
     * @return contents of that file
     * @throws IOException when file does not exist
     * @throws NullPointerException when file name is null
     */
    public static String getTextFromFile(String fileName) {
        if (fileName == null) {
            throw new NullPointerException();
        }
        // Use string buffer for efficiency
        StringBuffer buf = new StringBuffer();
        // One char at a time because of buggy reading (no performance loss - tested)
        byte[] buffer = new byte[1];
        FileConnection file = null;
        DataInputStream stream = null;
        try {
            file = (FileConnection) Connector.open("file:///" + fileName, Connector.READ);
            // Makes sure file exists so it doesn't try sneaky things
            if (!file.exists()) {
                throw new IOException(fileName + " does not exist.");
            }
            stream = file.openDataInputStream();
            while (stream.read(buffer) != -1) {
                buf.append(new String(buffer));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // This is almost always a problem with files and not IO problems
            // User should not have to deal with it
            return null;
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return buf.toString();
    }

    /**
     * Writes the text as the full text file. Everything else is deleted.
     *
     * @param fileName file name from the root directory in the cRIO
     * @param msg text to set the file to
     */
    public static void writeAsFile(String fileName, String msg) {
        if (fileName == null) {
            throw new NullPointerException();
        }
        FileConnection file = null;
        DataOutputStream stream = null;
        BufferedWriter writer = null;
        try {
            file = (FileConnection) Connector.open("file:///" + fileName, Connector.WRITE);
            file.create();
            stream = file.openDataOutputStream();

            writer = new BufferedWriter(new OutputStreamWriter(stream));
            writer.write(msg);
            writer.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (stream != null) {
                    stream.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Writes the text at the very end of the file.
     *
     * @param fileName file name from the root directory in the cRIO
     * @param msg text to add to the end of the file
     */
    public static void appendToFile(String fileName, String msg) {
        // Inificient but the only way that works after reboots...
        // Pull request if you know a better solution!
        writeAsFile(fileName, getTextFromFile(fileName) + msg);
    }

    /**
     * Writes the text to a new line at the end of the file.
     *
     * @param fileName file name from the root directory in the cRIO
     * @param msg text to add to the end of the file
     */
    public static void appendToNewLine(String fileName, String msg) {
        appendToFile(fileName, '\n' + msg);
    }
}
