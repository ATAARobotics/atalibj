package edu.ATA.main;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.DriverStationLCD;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 * Static utility class used to access the various forms of logging that are
 * used. Is capable of logging messages to the log file (available in
 * {@link Logger#PATH} through ftp), sending messages to the DriverStation
 * console (box on the right side of the program) and printing .
 *
 * <p> Logs are sent with different urgencies, which influence how they are sent
 * to the user. Urgency constants are available in {@link Urgency}, in an
 * enum-like format.
 *
 * <p> To retrieve the log file contents, use FTP or {@link Logger#getLog()}.
 *
 * @author Joel Gallant
 */
public final class Logger {

    private static final String PATH = "file:///log.txt";
    private static boolean fileLoggingOn = true;
    private static int lineNum = 1;
    private static FileConnection logFile;
    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;

    // cannot be subclassed or instantiated
    private Logger() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Class representing the urgency of the log. The reason for this
     * implementation is to ensure that logging does not encounter an error when
     * looking at its urgency. (Ex. int could be out of range, etc.) This keeps
     * the only possible values to be pre-approved by this class. (No errors
     * when logging)
     *
     * <p> <b> Null urgency objects are possible, but should throw an error
     * anyways. </b>
     */
    public final static class Urgency {

        private Urgency() {
        }
        /**
         * Urgent message. Displayed on DriverStation. (Sent to log and console)
         */
        public final static Urgency URGENT = new Urgency();
        /**
         * Warning message. Displayed on DriverStation. (Sent to log and
         * console)
         */
        public final static Urgency WARNING = new Urgency();
        /**
         * User message. Displayed on DriverStation. (Sent to log and console)
         */
        public final static Urgency USERMESSAGE = new Urgency();
        /**
         * Status report for logs. Is not displayed to user. (Sent to log and
         * console)
         */
        public final static Urgency STATUSREPORT = new Urgency();
        /**
         * Log message. Is not displayed to user. (Sent to console)
         */
        public final static Urgency LOG = new Urgency();
    }

    /**
     * Logs the message according to the urgency. There are three different ways
     * to log messages:
     *
     * <pre>
     * 1. Send to the console (netbeans console that code was deployed on
     *
     * 2. Send to the log file ({@link Logger#PATH})
     *
     * 3. Send to the DriverStation console (box on the right side)
     * </pre>
     *
     * The urgency and logging methods are as follows:
     *
     * <pre>
     * {@link Urgency#URGENT} - 1, 2, 3
     *
     * {@link Urgency#WARNING} - 1, 2, 3
     *
     * {@link Urgency#USERMESSAGE} - 1, 2, 3
     *
     * {@link Urgency#STATUSREPORT} - 1, 2
     *
     * {@link Urgency#LOG} - 2
     * </pre>
     *
     * @param urgency representation of how the message should be delivered
     * @param msg message to log
     */
    public static void log(Urgency urgency, String msg) {
        String usrMsg = "";
        if (urgency == Urgency.URGENT) {
            usrMsg = "Urgent@" + DriverstationInfo.getMatchTime() + " - " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.WARNING) {
            usrMsg = "Warning@" + DriverstationInfo.getMatchTime() + " - " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.USERMESSAGE) {
            usrMsg = "User Message - " + msg;
            displayLCDMessage(usrMsg);
        } else if (urgency == Urgency.STATUSREPORT) {
            usrMsg = "Status@" + DriverstationInfo.getMatchTime() + " - " + msg;
        } else if (urgency == Urgency.LOG) {
            usrMsg = "Log@" + DriverstationInfo.getMatchTime() + " - " + msg;
        }
        if (urgency != Urgency.LOG) {
            System.out.println(usrMsg);
        }
        try {
            if (fileLoggingOn) {
                logFile(msg.concat("\n"));
            }
        } catch (IOException ex) {
            System.err.println("!!!ERROR WHILE WRITING TO LOG FILE!!!\n" + ex.getMessage());
        }
    }

    /**
     * Appends a message to the log file on a new line. The log file is
     * available through FTP on {@link Logger#PATH}.
     *
     * @param msg message to append to the file
     * @throws IOException thrown when an error occurs while writing to the file
     */
    public static void logFile(String msg) throws IOException {
        if (logFile == null) {
            logFile = (FileConnection) Connector.open(PATH, Connector.READ_WRITE);
            outputStream = logFile.openDataOutputStream();
        }
        appendToFile(msg, outputStream);
    }

    /**
     * Appends a string to the text file that is connected in
     * {@code fileConnection}. Assumes that the file connection has not been
     * 'created' ({@link FileConnection#create()}).
     *
     * @param msg message to insert as data to the end of the text file
     * @param fileConnection connection to the text file
     * @throws IOException thrown when file connection cannot be initiated or
     * writing to the file fails
     */
    public static void appendToFile(String msg, DataOutputStream outputStream) throws IOException {
        byte[] data = msg.getBytes();
        try {
            // Need to test to find out if offset should be saved to append
            outputStream.write(data);
        } catch (IOException ex) {
        }
    }

    /**
     * Returns the full text from the log file.
     *
     * @see Logger#logFile(java.lang.String)
     * @throws IOException thrown when connection cannot be created or the file
     * cannot be accessed / read from
     * @return text in the log file
     */
    public static String getLog() throws IOException {
        if (logFile == null) {
            logFile = (FileConnection) Connector.open(PATH, Connector.READ_WRITE);
            inputStream = logFile.openDataInputStream();
        }
        return getTextFromFile(inputStream);
    }

    /**
     * Returns the full text from a text file based on its
     * {@link FileConnection}.
     *
     * @throws IOException thrown when connection cannot be created or file
     * cannot be accessed / read from
     * @param fileConnection connection to the file
     * @return text text in the text file
     */
    public static String getTextFromFile(DataInputStream inputStream) throws IOException {
        String tmp = "";
        byte cur;
        while ((cur = (byte) inputStream.read()) > 0) {
            tmp += (char) cur;
        }
        return tmp;
    }

    /**
     * Displays a string on the {@link DriverStationLCD}. This is the box on the
     * DriverStation on the right side. <b> Messages cannot be over
     * {@link DriverStationLCD#kLineLength}.</b> Messages are displayed on the
     * next line, meaning that if the previous message was displayed on line
     * <i>x</i>, the next message by convention is displayed on line <i>x+1</i>,
     * etc.
     *
     * @param msg message to display on next line
     */
    public static void displayLCDMessage(String msg) {
        DriverStationLCD.Line line;
        switch (lineNum) {
            case (1):
                line = DriverStationLCD.Line.kUser1;
                break;
            case (2):
                line = DriverStationLCD.Line.kUser2;
                break;
            case (3):
                line = DriverStationLCD.Line.kUser3;
                break;
            case (4):
                line = DriverStationLCD.Line.kUser4;
                break;
            case (5):
                line = DriverStationLCD.Line.kUser5;
                break;
            case (6):
                line = DriverStationLCD.Line.kUser6;
                break;
            default:
                line = DriverStationLCD.Line.kUser1;
        }
        DriverStationLCD.getInstance().println(line, 1, msg + "         ");
        DriverStationLCD.getInstance().updateLCD();
        if (++lineNum > 6) {
            lineNum = 1;
        }
    }

    /**
     * Sets whether or not logging through
     * {@link Logger#log(edu.ATA.main.Logger.Urgency, java.lang.String) log(Urgency, String)}
     * will log to the file at {@link Logger#PATH}. This is enabled by default.
     *
     * @param fileLoggingOn whether messages should be logged to the log file
     */
    public static void setFileLoggingOn(boolean fileLoggingOn) {
        Logger.fileLoggingOn = fileLoggingOn;
    }

    /**
     * Returns whether or not
     * {@link Logger#log(edu.ATA.main.Logger.Urgency, java.lang.String) log(Urgency, String)}
     * will log to the file at {@link Logger#PATH}. This is enabled by default.
     *
     * @return whether messages are being logged to the log file
     */
    public static boolean isFileLoggingOn() {
        return fileLoggingOn;
    }
}
